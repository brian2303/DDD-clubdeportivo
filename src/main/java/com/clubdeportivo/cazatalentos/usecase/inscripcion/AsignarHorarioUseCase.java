package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.clubdeportivo.cazatalentos.domain.inscripcion.Inscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.InscripcionActivada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;

public class AsignarHorarioUseCase extends UseCase<TriggeredEvent<InscripcionActivada>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<InscripcionActivada> inputEvent) {
        var event = inputEvent.getDomainEvent();
        var inscripcion = Inscripcion.from(InscripcionId.of(event.aggregateRootId()),retrieveEvents());

        inscripcion.asignarHorario(event.getDeportistaId(),event.getOrdenPagoId());

        emit().onResponse(new ResponseEvents(inscripcion.getUncommittedChanges()));
    }
}

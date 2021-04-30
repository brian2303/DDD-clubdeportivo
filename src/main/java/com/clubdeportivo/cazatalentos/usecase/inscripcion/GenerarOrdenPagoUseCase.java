package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.clubdeportivo.cazatalentos.domain.inscripcion.Inscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;


public class GenerarOrdenPagoUseCase extends UseCase<TriggeredEvent<PreInscripcionRealizada>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<PreInscripcionRealizada> input) {

        var event = input.getDomainEvent();

        var inscripcion = Inscripcion.from(InscripcionId.of(event.aggregateRootId()),retrieveEvents());
        var service = getService(DeportistaOrdenPagoService.class).orElseThrow();
        var deportistaDatos = service.findById(event.getDeportistaId());

        inscripcion.generarOrdenPago(event.getMonto(),deportistaDatos.getNombres());

        emit().onResponse(new ResponseEvents(inscripcion.getUncommittedChanges()));
    }
}

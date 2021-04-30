package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.clubdeportivo.cazatalentos.domain.inscripcion.Inscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.command.RealizarPreInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;

public class RealizarPreInscripcionUseCase extends UseCase<RequestCommand<RealizarPreInscripcion>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<RealizarPreInscripcion> realizarPreInscripcionRequestCommand) {
        var command = realizarPreInscripcionRequestCommand.getCommand();
        var inscripcion = new Inscripcion(new InscripcionId(),
                command.getFechaInscripcion(),
                command.getDeportistaId(),
                command.getMonto(),
                command.getNombreDeporte()
        );
        emit().onResponse(new ResponseEvents(inscripcion.getUncommittedChanges()));
    }
}

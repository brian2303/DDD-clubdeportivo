package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.clubdeportivo.cazatalentos.domain.inscripcion.Inscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.command.ActivarInscripcion;

public class ActivarInscripcionUseCase extends UseCase<RequestCommand<ActivarInscripcion>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ActivarInscripcion> inputCommand) {
        var command = inputCommand.getCommand();
        var inscripcion = Inscripcion.from(command.getInscripcionId(),retrieveEvents());
        inscripcion.activarInscripcion(
                command.getInscripcionId(),
                command.getDeportistaId(),
                command.getOrdenPagoId(),
                command.getMonto()
        );
        emit().onResponse(new ResponseEvents(inscripcion.getUncommittedChanges()));
    }
}

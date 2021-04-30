package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.clubdeportivo.cazatalentos.domain.inscripcion.Inscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.command.ActualizarFechaPago;

public class ExtenderFechaPagoUseCase extends UseCase<RequestCommand<ActualizarFechaPago>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ActualizarFechaPago> inputCommand) {
        var command = inputCommand.getCommand();
        var inscripcion = Inscripcion.from(command.getInscripcionId(),retrieveEvents());
        inscripcion.extenderFechaLimitePago(command.getDia(),command.getMes(),command.getAnio());
        emit().onResponse(new ResponseEvents(inscripcion.getUncommittedChanges()));
    }
}

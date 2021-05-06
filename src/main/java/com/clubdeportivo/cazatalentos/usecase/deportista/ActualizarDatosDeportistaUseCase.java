package com.clubdeportivo.cazatalentos.usecase.deportista;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.clubdeportivo.cazatalentos.domain.deportista.Deportista;
import com.clubdeportivo.cazatalentos.domain.deportista.command.ActualizarDatosDeportista;

public class ActualizarDatosDeportistaUseCase extends UseCase<RequestCommand<ActualizarDatosDeportista>,ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ActualizarDatosDeportista> inputCommand) {
        var command = inputCommand.getCommand();
        var deportista = Deportista.from(command.getDeportistaId(),retrieveEvents());
        deportista.actualizarDatos(command.getNombresCompletos(),command.getFechaNacimiento());
        emit().onResponse(new ResponseEvents(deportista.getUncommittedChanges()));
    }
}

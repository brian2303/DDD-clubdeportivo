package com.clubdeportivo.cazatalentos.usecase.deportista;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.clubdeportivo.cazatalentos.domain.deportista.Deportista;
import com.clubdeportivo.cazatalentos.domain.deportista.command.CrearDeportista;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;

public class crearDeportistaUseCase extends UseCase<RequestCommand<CrearDeportista>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CrearDeportista> crearDeportistaRequestCommand) {
        var command = crearDeportistaRequestCommand.getCommand();
        var deportista = new Deportista(new DeportistaId(),command.getNombresDeportista(),command.getFechaNacimiento(),command.getPreExistencias());
        deportista.asignarResponsable(command.getNombresResponsable(),command.getTelefonoContacto(),command.getCorreo());
        emit().onResponse(new ResponseEvents(deportista.getUncommittedChanges()));
    }
}

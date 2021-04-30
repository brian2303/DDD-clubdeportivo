package com.clubdeportivo.cazatalentos.usecase.deportista;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.command.ActualizarDatosDeportista;
import com.clubdeportivo.cazatalentos.domain.deportista.events.DatosDeportistaActualizados;
import com.clubdeportivo.cazatalentos.domain.deportista.events.DeportistaCreado;
import com.clubdeportivo.cazatalentos.domain.deportista.events.ResponsableAsignado;
import com.clubdeportivo.cazatalentos.domain.deportista.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class actualizarDatosDeportistaUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void actualizarDatosDeportista(){
        var id = DeportistaId.of("2243",TipoDocumento.TARJETA_IDENTIDAD);
        var nombres = new NombresCompletos("Andres","Perez");
        var fechaNacimiento = new FechaNacimiento(15,9,2012);
        var command = new ActualizarDatosDeportista(id,fechaNacimiento,nombres);

        Mockito.when(repository.getEventsBy(id.value())).thenReturn(eventosAlmacenados());
        var useCase = new actualizarDatosDeportistaUseCase();
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(id.value())
                .syncExecutor(useCase,new RequestCommand<>(command)).orElseThrow().getDomainEvents();

        var event = (DatosDeportistaActualizados) events.get(0);

        Assertions.assertEquals(nombres.value(),event.getNombresCompletos().value());
        Assertions.assertEquals(fechaNacimiento.value().fechaNacimiento(),event.getFechaNacimiento().value().fechaNacimiento());
    }

    private List<DomainEvent> eventosAlmacenados() {
        return List.of(
            new DeportistaCreado(new NombresCompletos("pepe","perez"),new FechaNacimiento(10,6,2010),List.of()),
            new ResponsableAsignado(new NombresCompletos("andres","perez"),new TelefonoContacto("3102120321"),new CorreoElectronico("pepe@pepe.com"))
        );
    }
}
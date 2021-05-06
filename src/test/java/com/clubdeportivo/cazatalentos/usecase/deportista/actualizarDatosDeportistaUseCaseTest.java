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
import com.clubdeportivo.cazatalentos.usecase.UseCaseHandleBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class actualizarDatosDeportistaUseCaseTest extends UseCaseHandleBaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void actualizarDatosDeportista() throws InterruptedException {
        var id = DeportistaId.of("2243",TipoDocumento.TARJETA_IDENTIDAD);
        var nombres = new NombresCompletos("Andres","Perez");
        var fechaNacimiento = new FechaNacimiento(15,9,2012);
        var command = new ActualizarDatosDeportista(id,fechaNacimiento,nombres);

        Mockito.when(repository.getEventsBy(id.value())).thenReturn(eventosAlmacenados());
        var useCase = new ActualizarDatosDeportistaUseCase();
        useCase.addRepository(repository);

        UseCaseHandler.getInstance()
                .setIdentifyExecutor(id.value())
                .asyncExecutor(useCase,new RequestCommand<>(command))
                .subscribe(subscriber);
        Thread.sleep(2000);
        Mockito.verify(subscriber,Mockito.times(1)).onNext(eventCaptor.capture());
        var event = (DatosDeportistaActualizados) eventCaptor.getAllValues().get(0);

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
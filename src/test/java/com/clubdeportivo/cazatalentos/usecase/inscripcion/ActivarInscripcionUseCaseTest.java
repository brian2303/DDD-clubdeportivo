package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.Deportista;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TipoDocumento;
import com.clubdeportivo.cazatalentos.domain.inscripcion.command.ActivarInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.InscripcionActivada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.OrdenPagoGenerada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

@ExtendWith(MockitoExtension.class)
class ActivarInscripcionUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void activarInscripcion(){


        var deportistaId = DeportistaId.of("123", TipoDocumento.TARJETA_IDENTIDAD);
        var ordenPagoId = OrdenPagoId.of("sss");
        var monto = new Monto(120.000);
        var inscripcionId = InscripcionId.of("123");
        Mockito.when(repository.getEventsBy(inscripcionId.value())).thenReturn(eventosAlmacenados());

        var command = new ActivarInscripcion(deportistaId,ordenPagoId,monto,inscripcionId);
        var useCase = new ActivarInscripcionUseCase();
        useCase.addRepository(repository);
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(inscripcionId.value())
                .syncExecutor(useCase,new RequestCommand<>(command)).orElseThrow().getDomainEvents();
        var event = (InscripcionActivada) events.get(0);

        Assertions.assertEquals(deportistaId.value(),event.getDeportistaId().value());
        Assertions.assertEquals(ordenPagoId.value(),event.getOrdenPagoId().value());
        Assertions.assertEquals(monto.value(),event.getMonto().value());
    }

    private List<DomainEvent> eventosAlmacenados() {
        return List.of(
                new PreInscripcionRealizada(new FechaInscripcion(20,9,2021)
                        ,new DeportistaId(),new Monto(120.000),new NombreDeporte("Futbol")),
                new OrdenPagoGenerada(new Monto(120.000),"pepe")
        );
    }

    @Test
    void montoInsuficienteParaActivarInscripcion(){
        var deportistaId = DeportistaId.of("123", TipoDocumento.TARJETA_IDENTIDAD);
        var ordenPagoId = OrdenPagoId.of("sss");
        var monto = new Monto(105.000);
        var inscripcionId = InscripcionId.of("123");
        Mockito.when(repository.getEventsBy(inscripcionId.value())).thenReturn(eventosAlmacenados());

        var command = new ActivarInscripcion(deportistaId,ordenPagoId,monto,inscripcionId);
        var useCase = new ActivarInscripcionUseCase();
        useCase.addRepository(repository);
        Assertions.assertThrows(BusinessException.class,() ->{
            UseCaseHandler.getInstance().setIdentifyExecutor(inscripcionId.value())
                    .syncExecutor(useCase,new RequestCommand<>(command))
                    .orElseThrow();
        },"El valor cancelado es menor al valor de la inscripcion");
    }
}
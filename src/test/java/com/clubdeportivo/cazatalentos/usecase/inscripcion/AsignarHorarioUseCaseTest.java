package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AsignarHorarioUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void asignarHorario(){
        Mockito.when(repository.getEventsBy("rrr")).thenReturn(eventosAlmacenados());


        var event = new InscripcionActivada(InscripcionId.of("rrr"),new DeportistaId(),new OrdenPagoId(),new Monto(120.000));
        event.setAggregateRootId("rrr");
        var useCase = new AsignarHorarioUseCase();
        useCase.addRepository(repository);

        UseCaseHandler.getInstance().setIdentifyExecutor("rrr")
                .syncExecutor(useCase,new TriggeredEvent<>(event)).orElseThrow().getDomainEvents();

        Assertions.assertEquals(120.000,event.getMonto().value());
    }

    private List<DomainEvent> eventosAlmacenados() {
        return List.of(new PreInscripcionRealizada(new FechaInscripcion(20,9,2021),new DeportistaId(),
                        new Monto(120.000),
                        new NombreDeporte("Futbol")
                ),
                new OrdenPagoGenerada(new Monto(120.000),"pepe"),
                new InscripcionActivada(InscripcionId.of("rrr"),new DeportistaId(),new OrdenPagoId(),new Monto(120.000))
        );
    }
}
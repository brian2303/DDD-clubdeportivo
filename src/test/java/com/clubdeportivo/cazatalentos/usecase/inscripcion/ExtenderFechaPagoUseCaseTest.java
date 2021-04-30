package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TipoDocumento;
import com.clubdeportivo.cazatalentos.domain.inscripcion.command.ActualizarFechaPago;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.FechaRenovada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.OrdenPagoGenerada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExtenderFechaPagoUseCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void extenderFechaPago(){
        var inscripcionId = InscripcionId.of("rrr");
        var dia = 12;
        var mes = 8;
        var anio = 2021;
        var command = new ActualizarFechaPago(dia,mes,anio,inscripcionId);
        var useCase = new ExtenderFechaPagoUseCase();
        Mockito.when(repository.getEventsBy("rrr")).thenReturn(eventosAlmacenados());
        useCase.addRepository(repository);

        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(inscripcionId.value())
                .syncExecutor(useCase,new RequestCommand<>(command)).orElseThrow().getDomainEvents();

        var event = (FechaRenovada) events.get(0);

        Assertions.assertTrue(Objects.nonNull(event.aggregateRootId()));
        Assertions.assertEquals(dia,event.getDia());
        Assertions.assertEquals(mes,event.getMes());
        Assertions.assertEquals(anio,event.getAnio());
    }

    private List<DomainEvent> eventosAlmacenados() {
        return List.of(
                new PreInscripcionRealizada(new FechaInscripcion(30,05,2021),
                        DeportistaId.of("ppp", TipoDocumento.TARJETA_IDENTIDAD),
                        new Monto(120.000),
                        new NombreDeporte("Futbol")
                ),
                new OrdenPagoGenerada(new Monto(120.000),"andres")
        );
    }

}
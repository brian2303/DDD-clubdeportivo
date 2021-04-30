package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TipoDocumento;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.OrdenPagoGenerada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;
import com.clubdeportivo.cazatalentos.usecase.model.DeportistaOrdenPago;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GenerarOrdenPagoUseCaseTest {


    @Mock
    DomainEventRepository repositorio;

    @Mock
    DeportistaOrdenPagoService service;

    @Test
    void generarOrdenDePago(){

        var id = InscripcionId.of("rrr");

        DeportistaOrdenPago datosDeportista = new DeportistaOrdenPago("pepe","23-03-2010");
        ServiceBuilder serviceBuilder = new ServiceBuilder();
        serviceBuilder.addService(service);

        var useCase = new GenerarOrdenPagoUseCase();
        Mockito.when(service.findById(Mockito.any())).thenReturn(datosDeportista);
        Mockito.when(repositorio.getEventsBy(id.value())).thenReturn(eventosAlmacenados());
        useCase.addRepository(repositorio);
        useCase.addServiceBuilder(serviceBuilder);

        var fechaInscripcion = new FechaInscripcion(20,9,2021);
        var deportistaId = DeportistaId.of("123", TipoDocumento.TARJETA_IDENTIDAD);
        var monto = new Monto(120.000);
        var nombreDeporte = new NombreDeporte("Futbol");

        var event = new PreInscripcionRealizada(fechaInscripcion,deportistaId,monto,nombreDeporte);
        event.setAggregateRootId("rrr");

        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor("rrr")
                .syncExecutor(useCase,new TriggeredEvent<>(event)).orElseThrow().getDomainEvents();

        var ordenPagoGenerada = (OrdenPagoGenerada) events.get(0);


        Mockito.verify(service).findById(Mockito.any());
        Assertions.assertEquals(monto.value(),ordenPagoGenerada.getMonto().value());
        Assertions.assertEquals(datosDeportista.getNombres(), ordenPagoGenerada.getNombres());

    }

    private List<DomainEvent> eventosAlmacenados() {
        return List.of(
                new PreInscripcionRealizada(
                        new FechaInscripcion(20,9,2021),
                        DeportistaId.of("123",TipoDocumento.TARJETA_IDENTIDAD),
                        new Monto(120.000),
                        new NombreDeporte("Futbol")
                )
        );
    }

}
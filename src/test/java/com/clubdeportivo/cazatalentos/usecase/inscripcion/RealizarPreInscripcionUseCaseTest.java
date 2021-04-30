package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TipoDocumento;
import com.clubdeportivo.cazatalentos.domain.inscripcion.Inscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.command.RealizarPreInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealizarPreInscripcionUseCaseTest {

    @Test
    void realizarPreInscripcion(){

        var fechaInscripcion = new FechaInscripcion(05,9,2021);
        var monto = new Monto(120.000);
        var nombreDeporte = new NombreDeporte("Futbol");
        var deportistaId = DeportistaId.of("aaa", TipoDocumento.TARJETA_IDENTIDAD);

        var command = new RealizarPreInscripcion(fechaInscripcion,monto,nombreDeporte,deportistaId);
        var useCase = new RealizarPreInscripcionUseCase();

        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase,new RequestCommand<>(command)).orElseThrow().getDomainEvents();

        var preInscripcionRealizada = (PreInscripcionRealizada) events.get(0);

        Assertions.assertEquals(fechaInscripcion.value(),preInscripcionRealizada.getFechaInscripcion().value());
        Assertions.assertEquals(monto.value(),preInscripcionRealizada.getMonto().value());
        Assertions.assertEquals("aaa" +"-"+ TipoDocumento.TARJETA_IDENTIDAD,preInscripcionRealizada.getDeportistaId().value());
    }


    @Test
    void errorEnLaFechaPreInscripcion(){

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            new FechaInscripcion(26,3,2021);
        },
        "La fecha de inscripcion no puede ser anterior a la fecha actual");
    }
}
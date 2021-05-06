package com.clubdeportivo.cazatalentos.usecase.deportista;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.command.CrearDeportista;
import com.clubdeportivo.cazatalentos.domain.deportista.events.DeportistaCreado;
import com.clubdeportivo.cazatalentos.domain.deportista.events.ResponsableAsignado;
import com.clubdeportivo.cazatalentos.domain.deportista.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.YEARS;

class crearDeportistaUseCaseTest {

    @Test
    void crearDeportista(){

        var nombresDeportista = new NombresCompletos("brian","olarte");
        var fechaNacimiento = new FechaNacimiento(23,03,1995);
        var preExistencias = new ArrayList<PreExistencia>();
        var nombreResponsable = new NombresCompletos("andres","rojas");
        var telefonoResponsable = new TelefonoContacto("3112343212");
        var correo = new CorreoElectronico("andres@hotmail.com");

        var command = new CrearDeportista(
                nombresDeportista,
                fechaNacimiento,
                preExistencias,
                nombreResponsable,
                telefonoResponsable,
                correo
        );

        var useCase = new CrearDeportistaUseCase();
        List<DomainEvent> events = UseCaseHandler.getInstance().
                syncExecutor(useCase,new RequestCommand<>(command)).orElseThrow().getDomainEvents();
        var deportistaCreado = (DeportistaCreado) events.get(0);
        var responsableAsignado = (ResponsableAsignado) events.get(1);

        Assertions.assertEquals(nombresDeportista.value(),deportistaCreado.getNombresCompletos().value());
        Assertions.assertEquals(fechaNacimiento.value().fechaNacimiento(),deportistaCreado.getFechaNacimiento().value().fechaNacimiento());
        Assertions.assertEquals(0,deportistaCreado.getPreExistencias().size());
        Assertions.assertEquals(nombreResponsable.value(),responsableAsignado.getNombresCompletos().value());
        Assertions.assertEquals(telefonoResponsable.value(),responsableAsignado.getTelefonoContacto().value());
        Assertions.assertEquals(correo.value(),responsableAsignado.getCorreo().value());
    }

    @Test
    void calcularEdadDeportistaCuandoSeInstancieValueObjectFechaNacimiento(){
        var nacimiento = new FechaNacimiento(15,9,2002);
        var expected = Math.toIntExact(YEARS.between(LocalDate.of(2002,9,15),LocalDate.now()));
        Assertions.assertEquals(expected,nacimiento.value().edad());
    }


}
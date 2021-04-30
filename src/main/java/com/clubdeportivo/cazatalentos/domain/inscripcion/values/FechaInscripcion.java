package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaInscripcion implements ValueObject<String> {

    private final String fecha;

    public FechaInscripcion(int dia, int mes, int anio) {
        var fechaInscripcion = LocalDate.of(anio,mes,dia);
        if (fechaInscripcion.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inscripcion no puede ser anterior a la fecha actual");
        }
        this.fecha = fechaInscripcion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
    }

    @Override
    public String value() {
        return fecha;
    }
}

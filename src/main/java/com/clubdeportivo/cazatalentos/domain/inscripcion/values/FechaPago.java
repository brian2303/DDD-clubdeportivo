package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

public class FechaPago implements ValueObject<String> {

    private final String fecha;

    public FechaPago(int dia,int mes, int anio) {
        var fechaPago = LocalDate.of(requireNonNull(anio),requireNonNull(mes),requireNonNull(dia));
        if (fechaPago.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de pago no puede ser superior a la fecha actual");
        }
        this.fecha = fechaPago.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String value() {
        return fecha;
    }
}

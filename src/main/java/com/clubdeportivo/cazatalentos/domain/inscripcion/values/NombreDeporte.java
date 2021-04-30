package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class NombreDeporte implements ValueObject<String> {
    private final String nombre;

    public NombreDeporte(String nombre) {
        this.nombre = Objects.requireNonNull(nombre);
    }

    @Override
    public String value() {
        return nombre;
    }
}

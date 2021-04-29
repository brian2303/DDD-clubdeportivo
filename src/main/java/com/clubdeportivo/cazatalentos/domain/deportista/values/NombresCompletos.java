package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class NombresCompletos implements ValueObject<String> {

    private final String nombres;
    private final String apellidos;

    public NombresCompletos(String nombres, String apellidos) {
        this.nombres = Objects.requireNonNull(nombres);
        this.apellidos = Objects.requireNonNull(apellidos);
    }

    @Override
    public String value() {
        return nombres + " " + apellidos;
    }
}

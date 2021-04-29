package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class CorreoElectronico implements ValueObject<String>{

    private final String correo;

    public CorreoElectronico(String correo) {
        if (correo.isBlank()){
            throw new IllegalArgumentException("El correo no puede estar en blanco");
        }
        this.correo = Objects.requireNonNull(correo);
    }


    @Override
    public String value() {
        return correo;
    }
}

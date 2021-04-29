package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class PreExistencia implements ValueObject<String> {

    private final String preExistencia;

    public PreExistencia(String preExistencia) {
        if (preExistencia.isBlank()){
            throw new IllegalArgumentException("La pre existencia no puede ser un valor vacio");
        }
        this.preExistencia = Objects.requireNonNull(preExistencia);
    }

    @Override
    public String value() {
        return preExistencia;
    }
}

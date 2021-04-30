package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Monto implements ValueObject<Double> {

    private final Double monto;

    public Monto(Double monto) {
        this.monto = Objects.requireNonNull(monto);
    }

    @Override
    public Double value() {
        return null;
    }
}

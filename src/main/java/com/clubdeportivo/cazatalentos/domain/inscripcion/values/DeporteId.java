package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.Identity;
import co.com.sofka.domain.generic.ValueObject;

public class DeporteId extends Identity {

    private DeporteId(String uid){
        super(uid);
    }

    public DeporteId() {}

    public static DeporteId of(String uid){
        return new DeporteId(uid);
    }
}

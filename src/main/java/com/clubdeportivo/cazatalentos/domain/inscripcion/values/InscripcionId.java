package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.Identity;

public class InscripcionId extends Identity {

    private InscripcionId(String uid){
        super(uid);
    }

    public InscripcionId() {}

    public static InscripcionId of(String uid){
        return new InscripcionId(uid);
    }
}

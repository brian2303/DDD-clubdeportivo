package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.Identity;

public class OrdenPagoId extends Identity {

    private OrdenPagoId(String uid){
        super(uid);
    }

    public OrdenPagoId() {}

    public static OrdenPagoId of(String uid){
        return new OrdenPagoId(uid);
    }

}

package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.Identity;

public class DeportistaId extends Identity {

    private DeportistaId(String uid, TipoDocumento tipoDocumento){
        super(uid + "-" + tipoDocumento);
    }

    public DeportistaId() {
    }

    public static DeportistaId of(String uid, TipoDocumento tipoDocumento){
        return new DeportistaId(uid,tipoDocumento);
    }
}

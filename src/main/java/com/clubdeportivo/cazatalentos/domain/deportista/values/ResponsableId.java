package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.Identity;

public class ResponsableId extends Identity {

    private ResponsableId(String uid, TipoDocumento tipoDocumento){
        super(uid + "-" + tipoDocumento);
    }

    public ResponsableId() {}

    public static ResponsableId of(String uid, TipoDocumento tipoDocumento){
        return new ResponsableId(uid,tipoDocumento);
    }
}

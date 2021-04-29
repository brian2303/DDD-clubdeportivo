package com.clubdeportivo.cazatalentos.domain.deportista;

import co.com.sofka.domain.generic.Entity;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;
import com.clubdeportivo.cazatalentos.domain.deportista.values.ResponsableId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TelefonoContacto;

public class Responsable extends Entity<ResponsableId> {

    private NombresCompletos nombres;
    private TelefonoContacto telefono;

    public Responsable(ResponsableId id) {
        super(id);
    }
}

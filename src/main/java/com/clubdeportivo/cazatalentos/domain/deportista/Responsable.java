package com.clubdeportivo.cazatalentos.domain.deportista;

import co.com.sofka.domain.generic.Entity;
import com.clubdeportivo.cazatalentos.domain.deportista.values.CorreoElectronico;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;
import com.clubdeportivo.cazatalentos.domain.deportista.values.ResponsableId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TelefonoContacto;

public class Responsable extends Entity<ResponsableId> {

    private NombresCompletos nombres;
    private TelefonoContacto telefono;
    private CorreoElectronico correo;

    public Responsable(ResponsableId entityId, NombresCompletos nombres, TelefonoContacto telefono, CorreoElectronico correo) {
        super(entityId);
        this.nombres = nombres;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Responsable(ResponsableId id) {
        super(id);
    }
}

package com.clubdeportivo.cazatalentos.domain.deportista.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.CorreoElectronico;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;
import com.clubdeportivo.cazatalentos.domain.deportista.values.TelefonoContacto;

public class ResponsableAsignado extends DomainEvent {

    private final NombresCompletos nombresCompletos;
    private final TelefonoContacto telefonoContacto;
    private final CorreoElectronico correo;

    public ResponsableAsignado(NombresCompletos nombresCompletos, TelefonoContacto telefonoContacto, CorreoElectronico correo) {
        super("clubdeportivo.deportista.responsableasignado");
        this.nombresCompletos = nombresCompletos;
        this.telefonoContacto = telefonoContacto;
        this.correo = correo;
    }

    public NombresCompletos getNombresCompletos() {
        return nombresCompletos;
    }

    public TelefonoContacto getTelefonoContacto() {
        return telefonoContacto;
    }

    public CorreoElectronico getCorreo() {
        return correo;
    }
}

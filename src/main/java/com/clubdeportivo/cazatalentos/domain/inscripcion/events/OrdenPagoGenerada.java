package com.clubdeportivo.cazatalentos.domain.inscripcion.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaPago;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;

public class OrdenPagoGenerada extends DomainEvent {
    private final Monto monto;
    private final String nombres;

    public OrdenPagoGenerada(Monto monto,String nombres) {
        super("clubdeportivo.inscripcion.ordenpagogenerada");
        this.monto = monto;
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public Monto getMonto() {
        return monto;
    }

}

package com.clubdeportivo.cazatalentos.domain.inscripcion.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.OrdenPagoId;

public class InscripcionActivada extends DomainEvent {

    private final InscripcionId inscripcionId;
    private final DeportistaId deportistaId;
    private final OrdenPagoId ordenPagoId;
    private final Monto monto;

    public InscripcionActivada(InscripcionId inscripcionId, DeportistaId deportistaId, OrdenPagoId ordenPagoId, Monto monto) {
        super("clubdeportivo.inscripcion.inscripcionactivada");
        this.inscripcionId = inscripcionId;
        this.deportistaId = deportistaId;
        this.ordenPagoId = ordenPagoId;
        this.monto = monto;
    }

    public InscripcionId getInscripcionId() {
        return inscripcionId;
    }

    public DeportistaId getDeportistaId() {
        return deportistaId;
    }

    public OrdenPagoId getOrdenPagoId() {
        return ordenPagoId;
    }

    public Monto getMonto() {
        return monto;
    }
}

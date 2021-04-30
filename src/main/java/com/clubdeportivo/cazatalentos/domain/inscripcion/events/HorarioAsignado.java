package com.clubdeportivo.cazatalentos.domain.inscripcion.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.OrdenPagoId;

public class HorarioAsignado extends DomainEvent {

    private final DeportistaId deportistaId;
    private final OrdenPagoId ordenPagoId;

    public HorarioAsignado(DeportistaId deportistaId, OrdenPagoId ordenPagoId) {
        super("clubdeportivo.inscripcion.horarioasignado");

        this.deportistaId = deportistaId;
        this.ordenPagoId = ordenPagoId;
    }

    public DeportistaId getDeportistaId() {
        return deportistaId;
    }

    public OrdenPagoId getOrdenPagoId() {
        return ordenPagoId;
    }
}

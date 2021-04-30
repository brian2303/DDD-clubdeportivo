package com.clubdeportivo.cazatalentos.domain.inscripcion.command;

import co.com.sofka.domain.generic.Command;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.OrdenPagoId;

public class ActivarInscripcion implements Command {

    private final DeportistaId deportistaId;
    private final OrdenPagoId ordenPagoId;
    private final InscripcionId inscripcionId;
    private final Monto monto;

    public ActivarInscripcion(DeportistaId deportistaId, OrdenPagoId ordenPagoId, Monto monto,InscripcionId inscripcionId) {
        this.deportistaId = deportistaId;
        this.ordenPagoId = ordenPagoId;
        this.monto = monto;
        this.inscripcionId = inscripcionId;
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

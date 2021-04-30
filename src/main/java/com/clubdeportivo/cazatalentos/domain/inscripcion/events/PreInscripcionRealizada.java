package com.clubdeportivo.cazatalentos.domain.inscripcion.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;

public class PreInscripcionRealizada extends DomainEvent {

    private final FechaInscripcion fechaInscripcion;
    private final DeportistaId deportistaId;
    private final Monto monto;
    private final NombreDeporte nombreDeporte;

    public PreInscripcionRealizada(FechaInscripcion fechaInscripcion, DeportistaId deportistaId, Monto monto, NombreDeporte nombreDeporte) {
        super("clubdeportivo.inscripcion.preinscripcionrealizada");
        this.fechaInscripcion = fechaInscripcion;
        this.deportistaId = deportistaId;
        this.monto = monto;
        this.nombreDeporte = nombreDeporte;
    }

    public FechaInscripcion getFechaInscripcion() {
        return fechaInscripcion;
    }

    public DeportistaId getDeportistaId() {
        return deportistaId;
    }

    public Monto getMonto() {
        return monto;
    }

    public NombreDeporte getNombreDeporte() {
        return nombreDeporte;
    }
}

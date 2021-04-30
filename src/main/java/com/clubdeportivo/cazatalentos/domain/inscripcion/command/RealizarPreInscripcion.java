package com.clubdeportivo.cazatalentos.domain.inscripcion.command;

import co.com.sofka.domain.generic.Command;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;

public class RealizarPreInscripcion implements Command {

    private final FechaInscripcion fechaInscripcion;
    private final Monto monto;
    private final NombreDeporte nombreDeporte;
    private final DeportistaId deportistaId;

    public RealizarPreInscripcion(FechaInscripcion fechaInscripcion, Monto monto, NombreDeporte nombreDeporte, DeportistaId deportistaId) {
        this.fechaInscripcion = fechaInscripcion;
        this.monto = monto;
        this.nombreDeporte = nombreDeporte;
        this.deportistaId = deportistaId;
    }

    public FechaInscripcion getFechaInscripcion() {
        return fechaInscripcion;
    }

    public Monto getMonto() {
        return monto;
    }

    public NombreDeporte getNombreDeporte() {
        return nombreDeporte;
    }

    public DeportistaId getDeportistaId() {
        return deportistaId;
    }
}

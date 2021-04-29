package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.AggregateEvent;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.EstadoInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaPago;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;

public class Inscripcion extends AggregateEvent<InscripcionId> {

    private FechaInscripcion fechaInscripcion;
    private FechaPago fechaPago;
    private Deporte deporte;
    EstadoInscripcion estadoInscripcion;

    public Inscripcion(InscripcionId id, FechaInscripcion fechaInscripcion) {
        super(id);
        this.fechaInscripcion = fechaInscripcion;
    }
}

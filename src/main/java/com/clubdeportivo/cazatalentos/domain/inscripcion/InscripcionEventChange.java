package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.EventChange;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;

public class InscripcionEventChange extends EventChange {
    public InscripcionEventChange(Inscripcion inscripcion) {
        apply((PreInscripcionRealizada event) ->{
            inscripcion.fechaInscripcion = event.getFechaInscripcion();
            inscripcion.monto = event.getMonto();
            inscripcion.deportistaId = event.getDeportistaId();
        });
    }
}

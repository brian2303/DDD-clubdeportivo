package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.Entity;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.DeporteId;

public class Deporte extends Entity<DeporteId> {
    public Deporte(DeporteId entityId) {
        super(entityId);
    }
}

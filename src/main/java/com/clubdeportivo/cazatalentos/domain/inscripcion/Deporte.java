package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.Entity;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.DeporteId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;

public class Deporte extends Entity<DeporteId> {

    private NombreDeporte nombreDeporte;

    public Deporte(DeporteId entityId, NombreDeporte nombreDeporte) {
        super(entityId);
        this.nombreDeporte = nombreDeporte;
    }
}

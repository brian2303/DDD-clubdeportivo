package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.AggregateEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.*;

public class Inscripcion extends AggregateEvent<InscripcionId> {

    protected FechaInscripcion fechaInscripcion;
    protected FechaPago fechaPago;
    protected Deporte deporte;
    protected DeportistaId deportistaId;
    protected Monto monto;
    protected EstadoInscripcion estadoInscripcion;

    public Inscripcion(InscripcionId entityId, FechaInscripcion fechaInscripcion,DeportistaId deportistaId,Monto monto,NombreDeporte nombreDeporte) {
        super(entityId);
        appendChange(new PreInscripcionRealizada(fechaInscripcion,deportistaId,monto,nombreDeporte));
    }

    private Inscripcion(InscripcionId id){
        super(id);
        subscribe(new InscripcionEventChange(this));
    }
}

package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.OrdenPagoGenerada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.*;

import java.util.List;

public class Inscripcion extends AggregateEvent<InscripcionId> {

    protected FechaInscripcion fechaInscripcion;
    protected Deporte deporte;
    protected DeportistaId deportistaId;
    protected Monto monto;
    protected EstadoInscripcion estadoInscripcion;
    protected OrdenPago ordenPago;

    public Inscripcion(InscripcionId entityId, FechaInscripcion fechaInscripcion,DeportistaId deportistaId,Monto monto,NombreDeporte nombreDeporte) {
        super(entityId);
        appendChange(new PreInscripcionRealizada(fechaInscripcion,deportistaId,monto,nombreDeporte));
    }

    public void generarOrdenPago(Monto monto,String nombres) {
        appendChange(new OrdenPagoGenerada(monto,nombres)).apply();
    }

    public static Inscripcion from(InscripcionId id, List<DomainEvent> events) {
        var inscripcion = new Inscripcion(id);
        events.forEach(inscripcion::applyEvent);
        return inscripcion;
    }


    private Inscripcion(InscripcionId id){
        super(id);
        subscribe(new InscripcionEventChange(this));
    }
}

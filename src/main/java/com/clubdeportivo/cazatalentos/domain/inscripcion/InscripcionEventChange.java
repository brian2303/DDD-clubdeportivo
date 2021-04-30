package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.EventChange;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.OrdenPagoGenerada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.PreInscripcionRealizada;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.EstadoInscripcion;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaPago;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.OrdenPagoId;

import java.time.LocalDate;

public class InscripcionEventChange extends EventChange {
    public InscripcionEventChange(Inscripcion inscripcion) {
        apply((PreInscripcionRealizada event) ->{
            inscripcion.fechaInscripcion = event.getFechaInscripcion();
            inscripcion.monto = event.getMonto();
            inscripcion.deportistaId = event.getDeportistaId();
            inscripcion.estadoInscripcion = EstadoInscripcion.PENDIENTE;
        });
        apply((OrdenPagoGenerada event) ->{
            int diasDePlazo = 15;
            LocalDate fechaLimitePago = LocalDate.now().plusDays(diasDePlazo);
            inscripcion.ordenPago = new OrdenPago(
                    new OrdenPagoId(),
                    event.getMonto(),
                    new FechaPago(fechaLimitePago.getDayOfMonth(),fechaLimitePago.getMonth().getValue(),fechaLimitePago.getYear()),
                    event.getNombres()
            );
        });
    }
}

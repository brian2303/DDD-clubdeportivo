package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.domain.generic.EventChange;
import com.clubdeportivo.cazatalentos.domain.inscripcion.events.*;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.DeporteId;
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
            inscripcion.deporte = new Deporte(new DeporteId(),event.getNombreDeporte());
            inscripcion.estadoInscripcion = EstadoInscripcion.PENDIENTE;
        });
        apply((OrdenPagoGenerada event) ->{
            final int DIAS_PLAZO = 15;
            LocalDate fechaLimitePago = LocalDate.now().plusDays(DIAS_PLAZO);
            inscripcion.ordenPago = new OrdenPago(
                    new OrdenPagoId(),
                    event.getMonto(),
                    new FechaPago(fechaLimitePago.getDayOfMonth(),fechaLimitePago.getMonth().getValue(),fechaLimitePago.getYear()),
                    event.getNombres()
            );
        });

        apply((FechaRenovada event) -> {
            inscripcion.ordenPago.actualizarFechaLimitePago(event.getDia(),event.getMes(),event.getAnio());
        });

        apply((InscripcionActivada event) ->{
            double totalPago = inscripcion.ordenPago.monto.value();
            double pagoRealizado = event.getMonto().value();
            if (pagoRealizado < totalPago){
                throw new BusinessException(inscripcion.identity().value(),"El valor cancelado es menor al valor de la inscripcion");
            }
            inscripcion.estadoInscripcion = EstadoInscripcion.ACTIVA;
        });

        apply((HorarioAsignado event) ->{
            if (inscripcion.estadoInscripcion.equals(EstadoInscripcion.ANULADA)){
                throw new BusinessException(inscripcion.identity().value(),"No pude asignar un horario por que la inscripcion esta anulada");
            }
            inscripcion.deporte.asignarHorario();
        });
    }
}

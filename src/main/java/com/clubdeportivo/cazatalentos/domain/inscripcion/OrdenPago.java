package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.Entity;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.FechaPago;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.Monto;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.OrdenPagoId;


public class OrdenPago extends Entity<OrdenPagoId> {

    private Monto monto;
    private FechaPago fechaLimitePago;
    private String nombres;

    public OrdenPago(OrdenPagoId entityId, Monto monto, FechaPago fechaLimitePago,String nombres) {
        super(entityId);
        this.monto = monto;
        this.fechaLimitePago = fechaLimitePago;
        this.nombres = nombres;
    }

    protected void actualizarFechaLimitePago(int dia,int mes,int anio){
        this.fechaLimitePago = new FechaPago(dia, mes, anio);
    }
}

package com.clubdeportivo.cazatalentos.domain.inscripcion.events;

import co.com.sofka.domain.generic.DomainEvent;

public class FechaRenovada extends DomainEvent {

    private final int dia;
    private final int mes;
    private final int anio;

    public FechaRenovada(int dia, int mes, int anio) {
        super("clubdeportivo.inscripcion.fecharenovada");
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }
}

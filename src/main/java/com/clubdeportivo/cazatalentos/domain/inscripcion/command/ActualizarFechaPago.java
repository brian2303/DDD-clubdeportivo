package com.clubdeportivo.cazatalentos.domain.inscripcion.command;

import co.com.sofka.domain.generic.Command;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.InscripcionId;

public class ActualizarFechaPago implements Command {

    private final InscripcionId inscripcionId;
    private final int dia;
    private final int mes;
    private final int anio;

    public ActualizarFechaPago(int dia, int mes, int anio,InscripcionId inscripcionId) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.inscripcionId = inscripcionId;
    }

    public InscripcionId getInscripcionId() {
        return inscripcionId;
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

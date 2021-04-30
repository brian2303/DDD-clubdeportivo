package com.clubdeportivo.cazatalentos.domain.deportista.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.FechaNacimiento;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;

public class DatosDeportistaActualizados extends DomainEvent {

    private final NombresCompletos nombresCompletos;
    private final FechaNacimiento fechaNacimiento;

    public DatosDeportistaActualizados(NombresCompletos nombresCompletos, FechaNacimiento fechaNacimiento) {
        super("clubdeportivo.deportista.datosdeportistaactualizados");
        this.nombresCompletos = nombresCompletos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public NombresCompletos getNombresCompletos() {
        return nombresCompletos;
    }

    public FechaNacimiento getFechaNacimiento() {
        return fechaNacimiento;
    }
}

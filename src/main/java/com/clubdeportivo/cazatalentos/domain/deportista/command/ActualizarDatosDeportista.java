package com.clubdeportivo.cazatalentos.domain.deportista.command;

import co.com.sofka.domain.generic.Command;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.FechaNacimiento;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;

public class ActualizarDatosDeportista implements Command {

    private final DeportistaId deportistaId;
    private final FechaNacimiento fechaNacimiento;
    private final NombresCompletos nombresCompletos;

    public ActualizarDatosDeportista(DeportistaId deportistaId, FechaNacimiento fechaNacimiento, NombresCompletos nombresCompletos) {
        this.deportistaId = deportistaId;
        this.fechaNacimiento = fechaNacimiento;
        this.nombresCompletos = nombresCompletos;
    }

    public DeportistaId getDeportistaId() {
        return deportistaId;
    }

    public FechaNacimiento getFechaNacimiento() {
        return fechaNacimiento;
    }

    public NombresCompletos getNombresCompletos() {
        return nombresCompletos;
    }
}

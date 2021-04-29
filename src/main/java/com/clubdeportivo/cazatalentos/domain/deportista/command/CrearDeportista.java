package com.clubdeportivo.cazatalentos.domain.deportista.command;

import co.com.sofka.domain.generic.Command;
import com.clubdeportivo.cazatalentos.domain.deportista.values.*;

import java.util.List;

public class CrearDeportista implements Command {
    private final NombresCompletos nombresDeportista;
    private final FechaNacimiento fechaNacimiento;
    private final List<PreExistencia> preExistencias;
    private final NombresCompletos nombresResponsable;
    private final TelefonoContacto telefonoContacto;
    private final CorreoElectronico correo;

    public CrearDeportista(NombresCompletos nombresDeportista, FechaNacimiento fechaNacimiento, List<PreExistencia> preExistencias, NombresCompletos nombresResponsable, TelefonoContacto telefonoContacto, CorreoElectronico correo) {
        this.nombresDeportista = nombresDeportista;
        this.fechaNacimiento = fechaNacimiento;
        this.preExistencias = preExistencias;
        this.nombresResponsable = nombresResponsable;
        this.telefonoContacto = telefonoContacto;
        this.correo = correo;
    }

    public NombresCompletos getNombresDeportista() {
        return nombresDeportista;
    }

    public FechaNacimiento getFechaNacimiento() {
        return fechaNacimiento;
    }

    public List<PreExistencia> getPreExistencias() {
        return preExistencias;
    }

    public NombresCompletos getNombresResponsable() {
        return nombresResponsable;
    }

    public TelefonoContacto getTelefonoContacto() {
        return telefonoContacto;
    }

    public CorreoElectronico getCorreo() {
        return correo;
    }
}

package com.clubdeportivo.cazatalentos.domain.deportista.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.FechaNacimiento;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;
import com.clubdeportivo.cazatalentos.domain.deportista.values.PreExistencia;

import java.util.List;

public class DeportistaCreado extends DomainEvent {

    private final NombresCompletos nombresCompletos;
    private final FechaNacimiento fechaNacimiento;
    private final List<PreExistencia> preExistencias;

    public DeportistaCreado(NombresCompletos nombresCompletos, FechaNacimiento fechaNacimiento, List<PreExistencia> preExistencias) {
        super("clubdeportivo.deportista.deportistacreado");
        this.nombresCompletos = nombresCompletos;
        this.fechaNacimiento = fechaNacimiento;
        this.preExistencias = preExistencias;
    }

    public NombresCompletos getNombresCompletos() {
        return nombresCompletos;
    }

    public FechaNacimiento getFechaNacimiento() {
        return fechaNacimiento;
    }

    public List<PreExistencia> getPreExistencias() {
        return preExistencias;
    }
}

package com.clubdeportivo.cazatalentos.domain.deportista;

import co.com.sofka.domain.generic.AggregateEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.domain.deportista.values.FechaNacimiento;
import com.clubdeportivo.cazatalentos.domain.deportista.values.NombresCompletos;
import com.clubdeportivo.cazatalentos.domain.deportista.values.Patologia;

import java.util.List;

public class Deportista extends AggregateEvent<DeportistaId> {

    private NombresCompletos nombresCompletos;
    private FechaNacimiento fechaNacimiento;
    private Responsable responsable;
    private List<Patologia> patologias;

    public Deportista(DeportistaId id, NombresCompletos nombresCompletos, FechaNacimiento fechaNacimiento) {
        super(id);
        this.nombresCompletos = nombresCompletos;
        this.fechaNacimiento = fechaNacimiento;
    }

    private Deportista(DeportistaId id) {
        super(id);
        subscribe(new DeportistaEventChange(this));
    }
}

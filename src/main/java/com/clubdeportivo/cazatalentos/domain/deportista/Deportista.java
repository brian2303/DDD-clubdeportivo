package com.clubdeportivo.cazatalentos.domain.deportista;

import co.com.sofka.domain.generic.AggregateEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.events.DeportistaCreado;
import com.clubdeportivo.cazatalentos.domain.deportista.events.ResponsableAsignado;
import com.clubdeportivo.cazatalentos.domain.deportista.values.*;

import java.util.List;

public class Deportista extends AggregateEvent<DeportistaId> {

    protected NombresCompletos nombresCompletos;
    protected FechaNacimiento fechaNacimiento;
    protected Responsable responsable;
    protected List<PreExistencia> preExistencias;

    public Deportista(DeportistaId id, NombresCompletos nombresCompletos, FechaNacimiento fechaNacimiento, List<PreExistencia> preExistencias) {
        super(id);
        appendChange(new DeportistaCreado(nombresCompletos,fechaNacimiento,preExistencias)).apply();
    }

    public void asignarResponsable(NombresCompletos nombresCompletos, TelefonoContacto telefonoContacto,CorreoElectronico correo){
        appendChange(new ResponsableAsignado(nombresCompletos,telefonoContacto,correo));
    }

    private Deportista(DeportistaId id) {
        super(id);
        subscribe(new DeportistaEventChange(this));
    }
}

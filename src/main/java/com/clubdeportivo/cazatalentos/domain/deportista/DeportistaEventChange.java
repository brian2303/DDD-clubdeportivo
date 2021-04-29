package com.clubdeportivo.cazatalentos.domain.deportista;

import co.com.sofka.domain.generic.EventChange;
import com.clubdeportivo.cazatalentos.domain.deportista.events.DeportistaCreado;
import com.clubdeportivo.cazatalentos.domain.deportista.events.ResponsableAsignado;
import com.clubdeportivo.cazatalentos.domain.deportista.values.ResponsableId;

public class DeportistaEventChange extends EventChange {

    public DeportistaEventChange(Deportista deportista) {
        apply((DeportistaCreado event) ->{
            deportista.nombresCompletos = event.getNombresCompletos();
            deportista.fechaNacimiento = event.getFechaNacimiento();
            deportista.preExistencias = event.getPreExistencias();
        });

        apply((ResponsableAsignado event)->{
            deportista.responsable = new Responsable(new ResponsableId(),
                    event.getNombresCompletos(),
                    event.getTelefonoContacto(),
                    event.getCorreo()
            );
        });
    }
}

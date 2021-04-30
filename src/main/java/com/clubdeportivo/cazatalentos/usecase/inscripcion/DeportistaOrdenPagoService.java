package com.clubdeportivo.cazatalentos.usecase.inscripcion;

import com.clubdeportivo.cazatalentos.domain.deportista.values.DeportistaId;
import com.clubdeportivo.cazatalentos.usecase.model.DeportistaOrdenPago;

public interface DeportistaOrdenPagoService {
    DeportistaOrdenPago findById(DeportistaId deportistaId);
}

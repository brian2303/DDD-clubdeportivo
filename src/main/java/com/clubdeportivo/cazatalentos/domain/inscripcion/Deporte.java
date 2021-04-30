package com.clubdeportivo.cazatalentos.domain.inscripcion;

import co.com.sofka.domain.generic.Entity;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.DeporteId;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.HorarioEntrenamiento;
import com.clubdeportivo.cazatalentos.domain.inscripcion.values.NombreDeporte;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;

public class Deporte extends Entity<DeporteId> {

    private NombreDeporte nombreDeporte;
    private HorarioEntrenamiento horario;

    public Deporte(DeporteId entityId, NombreDeporte nombreDeporte) {
        super(entityId);
        this.nombreDeporte = nombreDeporte;
        this.horario = new HorarioEntrenamiento(new HashMap<>());
    }

    public void asignarHorario() {
       this.horario.adicionarHorario(DayOfWeek.MONDAY, LocalTime.of(8,00,00));
       this.horario.adicionarHorario(DayOfWeek.WEDNESDAY,LocalTime.of(8,30,00));
       this.horario.adicionarHorario(DayOfWeek.FRIDAY,LocalTime.of(10,00,00));
    }
}

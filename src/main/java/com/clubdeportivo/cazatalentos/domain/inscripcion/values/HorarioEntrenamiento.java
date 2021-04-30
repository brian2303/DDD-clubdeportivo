package com.clubdeportivo.cazatalentos.domain.inscripcion.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;

public class HorarioEntrenamiento implements ValueObject<HorarioEntrenamiento.Value> {

    private final HashMap<DayOfWeek,LocalTime> horario;

    public HorarioEntrenamiento(HashMap<DayOfWeek, LocalTime> horarioInicio) {
        this.horario = horarioInicio;
    }

    public void adicionarHorario(DayOfWeek dayOfWeek,LocalTime localTime){
        this.horario.put(dayOfWeek,localTime);
    }

    @Override
    public HorarioEntrenamiento.Value value() {
        return new Value() {
            @Override
            public HashMap<DayOfWeek, LocalTime> horario() {
                return horario();
            }
        };
    }

    interface Value{
        HashMap<DayOfWeek,LocalTime> horario();
    }

}

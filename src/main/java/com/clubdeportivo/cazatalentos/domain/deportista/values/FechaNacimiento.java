package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static java.time.temporal.ChronoUnit.YEARS;
import static  java.lang.Math.toIntExact;

public class FechaNacimiento implements ValueObject<FechaNacimiento.Value> {

    private final String fechaNacimiento;
    private final Integer edad;

    public FechaNacimiento(Integer dia,Integer mes,Integer anio) {
        this.fechaNacimiento = fechaToString(dia,mes,anio);
        this.edad = calcularEdad(LocalDate.of(anio,mes,dia));
    }

    private Integer calcularEdad(LocalDate fechaNacimiento){
        LocalDate fechaActual = LocalDate.now();
        return toIntExact(YEARS.between(fechaNacimiento,fechaActual));
    }

    private String fechaToString(Integer dia,Integer mes,Integer anio){
        var fechaNacimiento = LocalDate.of(anio,mes,dia);
        if (fechaNacimiento.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser superior a la fecha actual");
        }
        return  LocalDate.of(requireNonNull(anio),requireNonNull(mes),requireNonNull(dia))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    @Override
    public FechaNacimiento.Value value() {
        return new Value() {
            @Override
            public Integer edad() {
                return edad;
            }

            @Override
            public String fechaNacimiento() {
                return fechaNacimiento;
            }
        };
    }

    public interface Value{
        Integer edad();
        String fechaNacimiento();
    }
}

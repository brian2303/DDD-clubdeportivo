package com.clubdeportivo.cazatalentos.usecase.model;

public class DeportistaOrdenPago {

    private String nombres;
    private String fechaNacimiento;

    public DeportistaOrdenPago(String nombres, String fechaNacimiento) {
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}

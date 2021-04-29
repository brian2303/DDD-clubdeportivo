package com.clubdeportivo.cazatalentos.domain.deportista.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefonoContacto implements ValueObject<String>{

    private final String telefono;

    public TelefonoContacto(String telefono) {
        if (!esValido(telefono)){
            throw new IllegalArgumentException("El telefono debe contener 10 numeros del 0 al 9");
        }
        this.telefono = telefono;
    }

    private boolean esValido(String telefono){
        Pattern patronComprobacion = Pattern.compile("[0-9]{10}");
        Matcher coincide = patronComprobacion.matcher(telefono);
        return coincide.matches();
    }

    @Override
    public String value() {
        return telefono;
    }
}

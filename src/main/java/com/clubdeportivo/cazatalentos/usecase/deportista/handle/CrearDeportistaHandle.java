package com.clubdeportivo.cazatalentos.usecase.deportista.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import com.clubdeportivo.cazatalentos.domain.deportista.command.CrearDeportista;
import com.clubdeportivo.cazatalentos.domain.deportista.values.*;
import com.clubdeportivo.cazatalentos.usecase.deportista.CrearDeportistaUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CommandHandles
@CommandType(name = "clubdeportivo.deportista.crear", aggregate = "deportista")
public class CrearDeportistaHandle extends UseCaseExecutor {

    private RequestCommand<CrearDeportista> request;

    @Override
    public void run() {
        runUseCase(new CrearDeportistaUseCase(),request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var nombresDeportista = new NombresCompletos(args.get("nombresDeportista"),args.get("apellidosDeportista"));
        var fecha = args.get("fechaNacimiento").split("-");
        var dia = Integer.parseInt(fecha[0]);
        var mes = Integer.parseInt(fecha[1]);
        var anio = Integer.parseInt(fecha[2]);
        var fechaNacimiento = new FechaNacimiento(dia,mes,anio);
        var preExistenciasCommand = args.get("preExistencias").split(",");
        List<PreExistencia> preExistencias = new ArrayList<>();
        for (String p: preExistenciasCommand){
            var preExistencia = new PreExistencia(p);
            preExistencias.add(preExistencia);
        }
        NombresCompletos nombresResponsable = new NombresCompletos(args.get("nombresResponsable"),args.get("apellidosResponsable"));
        TelefonoContacto telefonoContacto = new TelefonoContacto(args.get("telefono"));
        CorreoElectronico correo = new CorreoElectronico(args.get("correo"));
        request = new RequestCommand<>(new CrearDeportista(nombresDeportista,
                fechaNacimiento,preExistencias,nombresResponsable,telefonoContacto,correo)
        );
    }

}

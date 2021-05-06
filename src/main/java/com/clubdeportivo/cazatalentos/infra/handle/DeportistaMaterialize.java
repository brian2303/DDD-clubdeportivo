package com.clubdeportivo.cazatalentos.infra.handle;

import co.com.sofka.domain.generic.DomainEvent;
import com.clubdeportivo.cazatalentos.domain.deportista.events.DeportistaCreado;
import com.clubdeportivo.cazatalentos.domain.deportista.events.ResponsableAsignado;
import com.clubdeportivo.cazatalentos.domain.deportista.values.PreExistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class DeportistaMaterialize {

    private static final String COLLECTION_NAME = "deportistas";
    private static final Logger logger = Logger.getLogger(DeportistaMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleEventDeportistaCreado(DeportistaCreado deportistaCreado) {
        logger.info("****** Handle event deportistaCreado");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", deportistaCreado.aggregateRootId());
        data.put("fechaNacimiento", deportistaCreado.getFechaNacimiento().value().fechaNacimiento());
        data.put("edad", deportistaCreado.getFechaNacimiento().value().edad());
        data.put("preExistencias",getPreExistencias(deportistaCreado.getPreExistencias()));
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventResponsableAsignado(ResponsableAsignado responsableAsignado){
        logger.info("****** Handle event responsableAsignado");
        Update update = new Update();
        update.set("nombresCompletos",responsableAsignado.getNombresCompletos().value());
        update.set("correo",responsableAsignado.getCorreo().value());
        update.set("telefonoContacto",responsableAsignado.getTelefonoContacto().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(responsableAsignado),update,COLLECTION_NAME);
    }

    private Map<String,String> getPreExistencias(List<PreExistencia> value){
        HashMap<String, String> preExistencias = new HashMap<>();
        value.forEach(preExistencia -> preExistencias.put(
                String.valueOf(Objects.hash(preExistencia.value())),
                preExistencia.value())
        );
        return preExistencias;
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}

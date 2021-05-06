package com.clubdeportivo.cazatalentos.infra.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}

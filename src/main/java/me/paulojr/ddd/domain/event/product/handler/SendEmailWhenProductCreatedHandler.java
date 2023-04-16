package me.paulojr.ddd.domain.event.product.handler;

import me.paulojr.ddd.domain.event.Event;
import me.paulojr.ddd.domain.event.EventHandler;

public class SendEmailWhenProductCreatedHandler implements EventHandler {

    @Override
    public void handle(Event<?> productCreatedEvent) {
        System.out.println("SENDING EMAIL TO admin@admin.com");
    }
}

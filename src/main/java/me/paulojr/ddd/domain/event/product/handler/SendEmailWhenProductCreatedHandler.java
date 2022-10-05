package me.paulojr.ddd.domain.event.product.handler;

import me.paulojr.ddd.domain.event.EventHandler;
import me.paulojr.ddd.domain.event.product.SendEmailWhenProductCreatedEvent;

public class SendEmailWhenProductCreatedHandler implements EventHandler<SendEmailWhenProductCreatedEvent> {

    @Override
    public void handle(SendEmailWhenProductCreatedEvent sendEmailWhenProductCreatedEvent) {
        System.out.println("SENDING EMAIL TO admin@admin.com");
    }
}

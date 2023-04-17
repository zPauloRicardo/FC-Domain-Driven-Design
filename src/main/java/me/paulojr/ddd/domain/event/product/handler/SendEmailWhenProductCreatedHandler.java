package me.paulojr.ddd.domain.event.product.handler;

import me.paulojr.ddd.domain.entity.product.Product;
import me.paulojr.ddd.domain.event.Event;
import me.paulojr.ddd.domain.event.EventHandler;

public class SendEmailWhenProductCreatedHandler implements EventHandler<Product> {

    @Override
    public void handle(Event<Product> productCreatedEvent) {
        System.out.println("SENDING EMAIL TO admin@admin.com");
    }
}

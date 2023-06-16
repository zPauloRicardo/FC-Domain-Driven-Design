package me.paulojr.ddd.domain.product.event.handler;

import me.paulojr.ddd.domain.product.entity.Product;
import me.paulojr.ddd.domain.shared.event.Event;
import me.paulojr.ddd.domain.shared.event.EventHandler;

public class SendEmailWhenProductCreatedHandler implements EventHandler<Product> {

    @Override
    public void handle(Event<Product> productCreatedEvent) {
        System.out.println("SENDING EMAIL TO admin@admin.com");
    }
}

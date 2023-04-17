package me.paulojr.ddd.domain.event.costumer.handler;

import lombok.extern.slf4j.Slf4j;
import me.paulojr.ddd.domain.entity.costumer.Costumer;
import me.paulojr.ddd.domain.event.Event;
import me.paulojr.ddd.domain.event.EventHandler;

@Slf4j
public class SendLogTwoWhenConstumerCreatedHandler implements EventHandler<Costumer> {
    @Override
    public void handle(Event<Costumer> t) {
        log.warn("Esse é o segundo console.log do evento: CustomerCreated");
    }
}

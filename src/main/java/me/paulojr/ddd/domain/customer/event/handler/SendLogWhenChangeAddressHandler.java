package me.paulojr.ddd.domain.customer.event.handler;

import lombok.extern.slf4j.Slf4j;
import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.shared.event.Event;
import me.paulojr.ddd.domain.shared.event.EventHandler;

@Slf4j
public class SendLogWhenChangeAddressHandler implements EventHandler<Customer> {

    @Override
    public void handle(Event<Customer> t) {
        log.warn("Endereço do cliete: " + t.getEventData().getId() + ", " + t.getEventData().getName() + " alterado para: " + t.getEventData().getAddress().toString());
    }
}

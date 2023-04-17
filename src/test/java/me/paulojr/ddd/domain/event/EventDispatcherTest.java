package me.paulojr.ddd.domain.event;

import me.paulojr.ddd.domain.entity.costumer.Address;
import me.paulojr.ddd.domain.entity.costumer.Costumer;
import me.paulojr.ddd.domain.entity.product.Product;
import me.paulojr.ddd.domain.event.costumer.CostumerChangeAddressEvent;
import me.paulojr.ddd.domain.event.costumer.CostumerCreatedEvent;
import me.paulojr.ddd.domain.event.costumer.handler.SendLogOneWhenConstumerCreatedHandler;
import me.paulojr.ddd.domain.event.costumer.handler.SendLogTwoWhenConstumerCreatedHandler;
import me.paulojr.ddd.domain.event.costumer.handler.SendLogWhenChangeAddressHandler;
import me.paulojr.ddd.domain.event.product.ProductCreatedEvent;
import me.paulojr.ddd.domain.event.product.handler.SendEmailWhenProductCreatedHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventDispatcherTest {

    @Test
    void shouldRegisterEventHandler() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers("productCreatedEvent").isEmpty());
        assertEquals(1, eventDispatcher.getEventHandlers("productCreatedEvent").size());
        assertEquals(eventHandler, eventDispatcher.getEventHandlers("productCreatedEvent").get(0));
    }

    @Test
    void shouldUnregisterEventHandler() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers("productCreatedEvent").isEmpty());
        assertEquals(eventHandler, eventDispatcher.getEventHandlers("productCreatedEvent").get(0));
        eventDispatcher.unregister("productCreatedEvent", eventHandler);
        assertEquals(0, eventDispatcher.getEventHandlers("productCreatedEvent").size());
    }

    @Test
    void shouldUnregisterAllHandlers() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers("productCreatedEvent").isEmpty());
        assertEquals(eventHandler, eventDispatcher.getEventHandlers("productCreatedEvent").get(0));
        eventDispatcher.unregisterAll();
        assertEquals(0, eventDispatcher.getEventHandlers("productCreatedEvent").size());
    }

    @Test
    void shouldUnregisterAllHandlersByEvent() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers("productCreatedEvent").isEmpty());
        assertEquals(eventHandler, eventDispatcher.getEventHandlers("productCreatedEvent").get(0));
        eventDispatcher.unregisterAll("productCreatedEvent");
        assertEquals(0, eventDispatcher.getEventHandlers("productCreatedEvent").size());
    }

    @Test
    void  shouldNotifyAllHandlers() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler eventHandler = Mockito.spy(new SendEmailWhenProductCreatedHandler());
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        final ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(LocalDateTime.now(), new Product("01","Produto 1", 10f));
        //Notify Executa o Handle do Evento passado
        eventDispatcher.notify(productCreatedEvent);
        //verifica se foi executado.
        Mockito.verify(eventHandler).handle(Mockito.any());

        final EventHandler eventHandlerCreateOne = Mockito.spy(new SendLogOneWhenConstumerCreatedHandler());
        final EventHandler eventHandlerCreateTwo = Mockito.spy(new SendLogTwoWhenConstumerCreatedHandler());
        final EventHandler eventHandlerChange = Mockito.spy(new SendLogWhenChangeAddressHandler());
        eventDispatcher.register("costumerCreatedEvent", eventHandlerCreateOne);
        eventDispatcher.register("costumerCreatedEvent", eventHandlerCreateTwo);
        eventDispatcher.register("costumerChangeAddressEvent", eventHandlerChange);


        final Costumer costumer = new Costumer("01","Costumer 1");
        final CostumerCreatedEvent costumerCreatedEvent = new CostumerCreatedEvent(LocalDateTime.now(), costumer);
        eventDispatcher.notify(costumerCreatedEvent);
        Mockito.verify(eventHandlerCreateOne).handle(Mockito.any());
        Mockito.verify(eventHandlerCreateTwo).handle(Mockito.any());


        costumer.changeAddress(new Address("Rua teste",  10, "93800-246", "Sapiranga"));
        final CostumerChangeAddressEvent costumerChangeAddressEvent = new CostumerChangeAddressEvent(LocalDateTime.now(), costumer);
        eventDispatcher.notify(costumerChangeAddressEvent);
        Mockito.verify(eventHandlerChange).handle(Mockito.any());
    }


}

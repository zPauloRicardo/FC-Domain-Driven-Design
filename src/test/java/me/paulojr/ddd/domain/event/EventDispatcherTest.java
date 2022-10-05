package me.paulojr.ddd.domain.event;

import me.paulojr.ddd.domain.event.product.SendEmailWhenProductCreatedEvent;
import me.paulojr.ddd.domain.event.product.handler.SendEmailWhenProductCreatedHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventDispatcherTest {

    @Test
    void shouldRegisterEventHandler() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler<SendEmailWhenProductCreatedEvent> eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers("productCreatedEvent").isEmpty());
        assertEquals(1, eventDispatcher.getEventHandlers("productCreatedEvent").size());
        assertEquals(eventHandler, eventDispatcher.getEventHandlers("productCreatedEvent").get(0));
    }

    @Test
    void shouldUnregisterEventHandler() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler<SendEmailWhenProductCreatedEvent> eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);
        assertNotNull(eventDispatcher.getEventHandlers("productCreatedEvent"));
        assertFalse(eventDispatcher.getEventHandlers("productCreatedEvent").isEmpty());
        assertEquals(eventHandler, eventDispatcher.getEventHandlers("productCreatedEvent").get(0));
        eventDispatcher.unregister("productCreatedEvent", eventHandler);
        assertEquals(0, eventDispatcher.getEventHandlers("productCreatedEvent").size());
    }

    @Test
    void  shouldNotifyAllHandlers() {
        final EventDispatcher eventDispatcher = new EventDispatcherImpl();
        final EventHandler<SendEmailWhenProductCreatedEvent> eventHandler = new SendEmailWhenProductCreatedHandler();
        eventDispatcher.register("productCreatedEvent", eventHandler);

    }


}

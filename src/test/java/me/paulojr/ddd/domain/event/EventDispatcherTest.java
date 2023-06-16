package me.paulojr.ddd.domain.event;

import me.paulojr.ddd.domain.customer.vo.Address;
import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.product.entity.Product;
import me.paulojr.ddd.domain.shared.event.EventDispatcher;
import me.paulojr.ddd.domain.shared.event.EventDispatcherImpl;
import me.paulojr.ddd.domain.shared.event.EventHandler;
import me.paulojr.ddd.domain.customer.event.CustomerChangeAddressEvent;
import me.paulojr.ddd.domain.customer.event.CustomerCreatedEvent;
import me.paulojr.ddd.domain.customer.event.handler.SendLogOneWhenCustomerCreatedHandler;
import me.paulojr.ddd.domain.customer.event.handler.SendLogTwoWhenCustomerCreatedHandler;
import me.paulojr.ddd.domain.customer.event.handler.SendLogWhenChangeAddressHandler;
import me.paulojr.ddd.domain.product.event.ProductCreatedEvent;
import me.paulojr.ddd.domain.product.event.handler.SendEmailWhenProductCreatedHandler;
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

        final EventHandler eventHandlerCreateOne = Mockito.spy(new SendLogOneWhenCustomerCreatedHandler());
        final EventHandler eventHandlerCreateTwo = Mockito.spy(new SendLogTwoWhenCustomerCreatedHandler());
        final EventHandler eventHandlerChange = Mockito.spy(new SendLogWhenChangeAddressHandler());
        eventDispatcher.register("customerCreatedEvent", eventHandlerCreateOne);
        eventDispatcher.register("customerCreatedEvent", eventHandlerCreateTwo);
        eventDispatcher.register("customerChangeAddressEvent", eventHandlerChange);


        final Customer customer = new Customer("01","customer 1");
        final CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(LocalDateTime.now(), customer);
        eventDispatcher.notify(customerCreatedEvent);
        Mockito.verify(eventHandlerCreateOne).handle(Mockito.any());
        Mockito.verify(eventHandlerCreateTwo).handle(Mockito.any());


        customer.changeAddress(new Address("Rua teste",  10, "93800-246", "Sapiranga"));
        final CustomerChangeAddressEvent customerChangeAddressEvent = new CustomerChangeAddressEvent(LocalDateTime.now(), customer);
        eventDispatcher.notify(customerChangeAddressEvent);
        Mockito.verify(eventHandlerChange).handle(Mockito.any());
    }


}

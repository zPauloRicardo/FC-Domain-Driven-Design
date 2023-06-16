package me.paulojr.ddd.domain.shared.event;

import java.util.List;

public interface EventDispatcher {

    void notify(Event<?> event);

    void register(String eventName, EventHandler eventHandler);

    void unregister(String eventName, EventHandler eventHandler);

    void unregisterAll(String eventName);

    void unregisterAll();

    List<EventHandler> getEventHandlers(String productCreatedEvent);
}

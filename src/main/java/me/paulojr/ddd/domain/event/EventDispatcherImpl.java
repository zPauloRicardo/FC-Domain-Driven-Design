package me.paulojr.ddd.domain.event;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class EventDispatcherImpl implements EventDispatcher {

    private final Map<String, List<EventHandler<?>>> eventHandlers = new HashMap<>();


    @Override
    public void notify(Event<?> event) {

    }

    @Override
    public void register(String eventName, EventHandler<?> eventHandler) {
        final List<EventHandler<?>> handlers = this.eventHandlers.getOrDefault(eventName, new ArrayList<>());
        handlers.add(eventHandler);
        eventHandlers.put(eventName, handlers);
    }

    @Override
    public void unregister(String eventName, EventHandler<?> eventHandler) {
        eventHandlers.getOrDefault(eventName, new ArrayList<>()).remove(eventHandler);
    }

    @Override
    public void unregisterAll(String eventName) {

    }

    @Override
    public void unregisterAll() {

    }

    @Override
    public List<EventHandler<?>> getEventHandlers(String productCreatedEvent) {
        return this.eventHandlers.getOrDefault(productCreatedEvent, new ArrayList<>());
    }
}

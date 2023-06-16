package me.paulojr.ddd.domain.shared.event;

import java.util.*;

public class EventDispatcherImpl implements EventDispatcher {

    private final Map<String, List<EventHandler>> eventHandlers = new HashMap<>();


    @Override
    public void notify(Event<?> event) {
        this.eventHandlers.getOrDefault(event.getClass().getSimpleName().toLowerCase(), Collections.emptyList()).forEach(eventEventHandler -> eventEventHandler.handle(event));
    }

    @Override
    public void register(String eventName, EventHandler eventHandler) {
        eventName = eventName.toLowerCase();
        final List<EventHandler> handlers = this.eventHandlers.getOrDefault(eventName, new ArrayList<>());
        handlers.add(eventHandler);
        eventHandlers.put(eventName, handlers);
    }

    @Override
    public void unregister(String eventName, EventHandler eventHandler) {
        eventName = eventName.toLowerCase();
        eventHandlers.getOrDefault(eventName, Collections.emptyList()).remove(eventHandler);
    }

    @Override
    public void unregisterAll(String eventName) {
        eventName = eventName.toLowerCase();
        eventHandlers.getOrDefault(eventName, Collections.emptyList()).clear();
    }

    @Override
    public void unregisterAll() {
        eventHandlers.clear();
    }

    @Override
    public List<EventHandler> getEventHandlers(String eventName) {
        eventName = eventName.toLowerCase();
        return this.eventHandlers.getOrDefault(eventName, new ArrayList<>());
    }
}

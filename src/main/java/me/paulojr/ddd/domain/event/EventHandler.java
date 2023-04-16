package me.paulojr.ddd.domain.event;

public interface EventHandler {

    void handle(Event<?> t);


}

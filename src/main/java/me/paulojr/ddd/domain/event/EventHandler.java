package me.paulojr.ddd.domain.event;

public interface EventHandler<T> {

    void handle(Event<T> t);


}

package me.paulojr.ddd.domain.shared.event;

public interface EventHandler<T> {

    void handle(Event<T> t);


}

package me.paulojr.ddd.domain.event;

public interface EventHandler<T extends Event<?>> {

    void handle(T t);


}

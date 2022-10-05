package me.paulojr.ddd.domain.event;

import java.time.LocalDateTime;

public interface Event<T> {


    LocalDateTime getDateTimeOccurred();
    T getEventData();

}

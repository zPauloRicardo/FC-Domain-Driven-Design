package me.paulojr.ddd.domain.event.costumer;

import me.paulojr.ddd.domain.entity.costumer.Costumer;
import me.paulojr.ddd.domain.event.Event;

import java.time.LocalDateTime;
import java.util.Objects;

public class CostumerChangeAddressEvent implements Event<Costumer> {

    private final LocalDateTime dateTime;
    private final Costumer data;

    public CostumerChangeAddressEvent(LocalDateTime dateTime, Costumer data) {
        this.dateTime = dateTime;
        this.data = data;
    }

    @Override
    public LocalDateTime getDateTimeOccurred() {
        return this.dateTime;
    }

    @Override
    public Costumer getEventData() {
        return this.data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostumerChangeAddressEvent that)) return false;

        if (!Objects.equals(dateTime, that.dateTime)) return false;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = dateTime != null ? dateTime.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}

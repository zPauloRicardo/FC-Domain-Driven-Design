package me.paulojr.ddd.domain.customer.event;

import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.shared.event.Event;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerChangeAddressEvent implements Event<Customer> {

    private final LocalDateTime dateTime;
    private final Customer data;

    public CustomerChangeAddressEvent(LocalDateTime dateTime, Customer data) {
        this.dateTime = dateTime;
        this.data = data;
    }

    @Override
    public LocalDateTime getDateTimeOccurred() {
        return this.dateTime;
    }

    @Override
    public Customer getEventData() {
        return this.data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerChangeAddressEvent that)) return false;

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

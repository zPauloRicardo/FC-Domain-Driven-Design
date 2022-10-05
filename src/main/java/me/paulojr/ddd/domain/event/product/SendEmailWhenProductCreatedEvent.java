package me.paulojr.ddd.domain.event.product;

import me.paulojr.ddd.domain.entity.product.Product;
import me.paulojr.ddd.domain.event.Event;

import java.time.LocalDateTime;
import java.util.Objects;

public class SendEmailWhenProductCreatedEvent implements Event<Product> {

    private final LocalDateTime dateTime;
    private final Product data;

    public SendEmailWhenProductCreatedEvent(LocalDateTime dateTime, Product data) {
        this.dateTime = dateTime;
        this.data = data;
    }

    @Override
    public LocalDateTime getDateTimeOccurred() {
        return this.dateTime;
    }

    @Override
    public Product getEventData() {
        return this.data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SendEmailWhenProductCreatedEvent)) return false;

        SendEmailWhenProductCreatedEvent that = (SendEmailWhenProductCreatedEvent) o;

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

package me.paulojr.ddd.domain.product.event;

import me.paulojr.ddd.domain.shared.event.Event;
import me.paulojr.ddd.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductCreatedEvent implements Event<Product> {

    private final LocalDateTime dateTime;
    private final Product data;

    public ProductCreatedEvent(LocalDateTime dateTime, Product data) {
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
        if (!(o instanceof ProductCreatedEvent)) return false;

        ProductCreatedEvent that = (ProductCreatedEvent) o;

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

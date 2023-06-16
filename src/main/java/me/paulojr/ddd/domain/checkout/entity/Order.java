package me.paulojr.ddd.domain.checkout.entity;

import java.util.List;
import java.util.Objects;

public class Order {

    private final String id;
    private final String customerId;
    private final List<OrderItem> items;

    public Order(String id, String customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.validate();
    }

    private void validate() {
        if (this.id == null || this.id.isBlank() || this.id.isEmpty())
            throw new IllegalArgumentException("Id não pode ser nulo.");
        if (this.customerId == null || this.customerId.isBlank() || this.customerId.isEmpty())
            throw new IllegalArgumentException("Id de cliente não pode ser nulo.");
        if (this.items == null || this.items.isEmpty())
            throw new IllegalArgumentException("Um pedido deve ter itens");
    }


    private float total() {
        return items.stream().reduce(0f, (aFloat, orderItem) -> orderItem.getPrice() + aFloat, Float::sum);
    }


    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Float getTotal() {
        return this.total();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!Objects.equals(id, order.id)) return false;
        if (!Objects.equals(customerId, order.customerId)) return false;
        return Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}

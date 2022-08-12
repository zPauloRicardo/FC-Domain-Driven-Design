package me.paulojr.ddd.domain.entity.order;

import java.util.List;

public class Order {

    private String id;
    private String costumerId;
    private List<OrderItem> items;
    private Float total;

    public Order(String id, String costumerId, List<OrderItem> items) {
        this.id = id;
        this.costumerId = costumerId;
        this.items = items;
        this.validate();
        this.total = this.total();
    }

    private void validate() {
        if (this.id == null || this.id.isBlank() || this.id.isEmpty())
            throw new IllegalArgumentException("Id não pode ser nulo.");
        if (this.costumerId == null || this.costumerId.isBlank() || this.costumerId.isEmpty())
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

    public String getCostumerId() {
        return costumerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Float getTotal() {
        return total;
    }
}

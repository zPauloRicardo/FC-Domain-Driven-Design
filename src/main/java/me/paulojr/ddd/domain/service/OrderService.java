package me.paulojr.ddd.domain.service;

import me.paulojr.ddd.domain.entity.costumer.Costumer;
import me.paulojr.ddd.domain.entity.order.Order;
import me.paulojr.ddd.domain.entity.order.OrderItem;

import java.util.List;
import java.util.UUID;

public class OrderService {
    public float total(List<Order> orders) {
        return orders.stream().reduce(0f, (aFloat, order) -> order.getTotal() + aFloat, Float::sum);
    }

    public Order placeOrder(Costumer costumer, List<OrderItem> items) {
        if(items == null || items.isEmpty())
            throw new IllegalArgumentException("Ordem deve ter pelo menos um item");
        final Order order = new Order(UUID.randomUUID().toString(), costumer.getId(), items);
        costumer.addReawardPoints(order.getTotal() / 2);
        return order;
    }
}

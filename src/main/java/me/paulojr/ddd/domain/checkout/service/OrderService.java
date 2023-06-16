package me.paulojr.ddd.domain.checkout.service;

import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.checkout.entity.Order;
import me.paulojr.ddd.domain.checkout.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public class OrderService {
    public float total(List<Order> orders) {
        return orders.stream().reduce(0f, (aFloat, order) -> order.getTotal() + aFloat, Float::sum);
    }

    public Order placeOrder(Customer customer, List<OrderItem> items) {
        if(items == null || items.isEmpty())
            throw new IllegalArgumentException("Ordem deve ter pelo menos um item");
        final Order order = new Order(UUID.randomUUID().toString(), customer.getId(), items);
        customer.addReawardPoints(order.getTotal() / 2);
        return order;
    }
}

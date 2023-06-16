package me.paulojr.ddd.infrastructure.order.jpa.repository;

import me.paulojr.ddd.domain.checkout.entity.Order;
import me.paulojr.ddd.domain.checkout.entity.OrderItem;
import me.paulojr.ddd.domain.checkout.repository.OrderRepository;
import me.paulojr.ddd.infrastructure.order.jpa.model.OrderModel;
import me.paulojr.ddd.infrastructure.order.jpa.repository.OrderRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryJpa orderRepositoryJpa;

    @Autowired
    public OrderRepositoryImpl(OrderRepositoryJpa orderRepositoryJpa) {
        this.orderRepositoryJpa = orderRepositoryJpa;
    }

    @Override
    public CompletableFuture<Void> create(Order entity) {
        return CompletableFuture.runAsync(() -> this.orderRepositoryJpa.save(new OrderModel(entity)));
    }

    @Override
    public CompletableFuture<Void> update(Order entity) {
        return CompletableFuture.runAsync(() -> this.orderRepositoryJpa.save(new OrderModel(entity)));
    }

    @Override
    public CompletableFuture<Order> find(String id) {
        return CompletableFuture.supplyAsync(() -> this.fromModel(this.orderRepositoryJpa.findById(id).orElse(null)));
    }

    @Override
    public CompletableFuture<List<Order>> findAll() {
        return CompletableFuture.supplyAsync(() -> this.orderRepositoryJpa.findAll().stream().map(this::fromModel).collect(Collectors.toList()));
    }

    private Order fromModel(OrderModel orderModel) {
        return orderModel != null ? new Order(orderModel.getId(), orderModel.getcustomer().getId(),
                orderModel
                        .getItems()
                        .stream()
                        .map(model -> new OrderItem(model.getId(), model.getName(), model.getPrice(), model.getProduct().getId(), model.getQuantity()))
                        .collect(Collectors.toList())) : null;
    }

}

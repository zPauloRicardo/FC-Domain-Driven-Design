package me.paulojr.ddd.infrastructure.order.jpa.model;


import me.paulojr.ddd.domain.checkout.entity.Order;

import jakarta.persistence.*;
import me.paulojr.ddd.infrastructure.customer.jpa.model.CustomerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "FC_ORDERS")
public class OrderModel {

    @Id
    @Column(name = "ID_ORDER")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ID_customer", nullable = false)
    private CustomerModel customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItemModel> items = new ArrayList<>();

    private Float total;

    public OrderModel() {
    }

    public OrderModel(Order order) {
        final CustomerModel customer = new CustomerModel();
        customer.setId(order.getCustomerId());
        this.id = order.getId();
        this.customer = customer;
        this.items = order.getItems().stream().map(orderItem -> new OrderItemModel(orderItem, this)).collect(Collectors.toList());
        this.total = order.getTotal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerModel getcustomer() {
        return customer;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<OrderItemModel> getItems() {
        return items;
    }
}

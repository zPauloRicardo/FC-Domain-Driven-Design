package me.paulojr.ddd.infrastructure.database.model;


import me.paulojr.ddd.domain.entity.order.Order;

import jakarta.persistence.*;
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
    @JoinColumn(name = "ID_COSTUMER", nullable = false)
    private CostumerModel costumer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItemModel> items = new ArrayList<>();

    private Float total;

    public OrderModel() {
    }

    public OrderModel(Order order) {
        final CostumerModel costumer = new CostumerModel();
        costumer.setId(order.getCostumerId());
        this.id = order.getId();
        this.costumer = costumer;
        this.items = order.getItems().stream().map(orderItem -> new OrderItemModel(orderItem, this)).collect(Collectors.toList());
        this.total = order.getTotal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CostumerModel getCostumer() {
        return costumer;
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

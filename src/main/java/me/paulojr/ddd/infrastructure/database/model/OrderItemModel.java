package me.paulojr.ddd.infrastructure.database.model;


import me.paulojr.ddd.domain.entity.order.OrderItem;

import javax.persistence.*;

@Entity
@Table(name = "FC_ORDER_ITEMS")
public class OrderItemModel {

    @Id
    @Column(name = "ID_ORDER_ITEM")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "ID_ORDER", nullable = false)
    private OrderModel order;

    private String name;

    private Float price;

    private Float quantity;

    public OrderItemModel() {
    }

    public OrderItemModel(OrderItem orderItem, OrderModel order) {
        final ProductModel product = new ProductModel();
        product.setId(orderItem.getProductId());
        this.id = orderItem.getId();
        this.product = product;
        this.order = order;
        this.name = orderItem.getName();
        this.price = orderItem.getUnitPrice();
        this.quantity = orderItem.getQuantity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}

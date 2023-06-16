package me.paulojr.ddd.infrastructure.product.jpa.model;


import me.paulojr.ddd.domain.product.entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FC_PRODUCTS")
public class ProductModel {

    @Id
    @Column(name = "ID_PRODUCT")
    private String id;
    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;
    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Float price;

    public ProductModel() {
    }

    public ProductModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }
}

package me.paulojr.ddd.infrastructure.database.model;


import me.paulojr.ddd.domain.entity.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

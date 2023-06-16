package me.paulojr.ddd.domain.product.service;

import me.paulojr.ddd.domain.product.entity.Product;

import java.util.List;

public class ProductService {
    public void increasePrice(List<Product> products, float percent) {
        products.forEach(
                product -> product.changePrice((product.getPrice() * percent) / 100 + product.getPrice())
        );
    }
}

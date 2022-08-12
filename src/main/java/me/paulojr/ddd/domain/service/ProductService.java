package me.paulojr.ddd.domain.service;

import me.paulojr.ddd.domain.entity.product.Product;

import java.util.List;

public class ProductService {
    public void increasePrice(List<Product> products, float percent) {
        products.forEach(
                product -> product.changePrice((product.getPrice() * percent) / 100 + product.getPrice())
        );
    }
}

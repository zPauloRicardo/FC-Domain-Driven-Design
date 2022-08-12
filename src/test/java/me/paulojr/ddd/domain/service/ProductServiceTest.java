package me.paulojr.ddd.domain.service;

import me.paulojr.ddd.domain.entity.product.Product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceTest {

    private static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = new ProductService();
    }

    @Test
    void shouldChangePriceOfAllProducts() {
        Product product1 = new Product("p1", "Produto 1", 10f);
        Product product2 = new Product("p2", "Produto 2", 20f);
        List<Product> products = List.of(product1, product2);
        productService.increasePrice(products, 100f);
        assertEquals(20f, product1.getPrice());
        assertEquals(40f, product2.getPrice());
    }

}

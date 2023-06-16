package me.paulojr.ddd.domain.service;

import me.paulojr.ddd.domain.product.service.ProductService;
import me.paulojr.ddd.domain.product.entity.Product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
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

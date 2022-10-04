package me.paulojr.ddd.domain.repository;

import me.paulojr.ddd.domain.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldFindProduct() {
        Product product = new Product("1", "Produto", 6.5f);
        productRepository.create(product).join();
        CompletableFuture<Product> saved = productRepository.find("1");
        product = saved.join();
        assertEquals(6.5f, product.getPrice());
        assertEquals("1", product.getId());
        assertEquals("Produto", product.getName());
    }

    @Test
    public void shouldCreateProduct() {
        Product product = new Product("1", "Produto", 6.5f);
        productRepository.create(product).join();
        CompletableFuture<Product> saved = productRepository.find("1");
        product = saved.join();
        assertEquals(6.5f, product.getPrice());
        assertEquals("1", product.getId());
        assertEquals("Produto", product.getName());
    }

    @Test
    public void shouldUpdateProduct() {
        Product product = new Product("1", "Produto", 6.5f);
        productRepository.create(product).join();
        CompletableFuture<Product> saved = productRepository.find("1");
        product = saved.join();
        product.changeName("Item");
        productRepository.update(product).join();
        product = productRepository.find("1").join();
        assertEquals(6.5f, product.getPrice());
        assertEquals("1", product.getId());
        assertEquals("Item", product.getName());
    }

    @Test
    public void shouldFindAllProducts() {
        Product product = new Product("1", "Produto", 6.5f);
        Product product2 = new Product("2", "Produto 2", 16.5f);
        productRepository.create(product).join();
        productRepository.create(product2).join();
        CompletableFuture<List<Product>> saved = productRepository.findAll();
        List<Product> ls = saved.join();
        List<Product> prods = List.of(product, product2);
        assertEquals(2, ls.size());
        assertTrue(prods.containsAll(ls));
    }

}

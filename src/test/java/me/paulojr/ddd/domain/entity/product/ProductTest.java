package me.paulojr.ddd.domain.entity.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void shouldThrowErrorWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Product(null, "123", 10.5f));
    }

    @Test
    void shouldThrowErrorWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123", null, 10.5f));
    }

    @Test
    void shouldThrowErrorWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123", "", 10.5f));
    }

    @Test
    void shouldThrowErrorWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123", " ", 10.5f));
    }

    @Test
    void shouldThrowErrorWhenPriceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123", "321", null));
    }

    @Test
    void shouldThrowErrorWhenPriceIsLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Product("123", "321", -15f));
    }

    @Test
    void shouldThrowErrorWhenChangePriceIsLessThanZero() {
        final Product product = new Product("123", "321", 150f);
        assertThrows(IllegalArgumentException.class, () -> product.changePrice(-15f));
    }

    @Test
    void shouldThrowErrorWhenChangePriceIsNull() {
        final Product product = new Product("123", "321", 150f);
        assertThrows(IllegalArgumentException.class, () -> product.changePrice(null));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsNull() {
        final Product product = new Product("123", "123", 150f);
        assertThrows(IllegalArgumentException.class, () -> product.changeName(null));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsEmpty() {
        final Product product = new Product("123", "123", 150f);
        assertThrows(IllegalArgumentException.class, () -> product.changeName(""));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsBlank() {
        final Product product = new Product("123", "123", 150f);
        assertThrows(IllegalArgumentException.class, () -> product.changeName("  "));
    }

    @Test
    void shouldCreateProduct() {
        assertInstanceOf(Product.class, new Product("123", "123", 10.5f));
    }

}

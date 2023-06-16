package me.paulojr.ddd.domain.entity.order;

import me.paulojr.ddd.domain.checkout.entity.Order;
import me.paulojr.ddd.domain.checkout.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void shouldThrowErrorWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Order(null, "123", List.of(new OrderItem("1", "Produto", 10.5f, "p1", 1f))));
    }

    @Test
    void shouldThrowErrorWhencustomerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Order("123", null, List.of(new OrderItem("1", "Produto", 10.5f, "p2", 2f))));
    }

    @Test
    void shouldThrowErrorWhenItemsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Order("123", "321", null));
    }

    @Test
    void shouldThrowErrorWhenItemsIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Order("123", "123", Collections.emptyList()));
    }

    @Test
    void shouldCalculateTotal() {
        final OrderItem item = new OrderItem("i1", "Item", 10.5f, "p1", 1f);
        final Order order = new Order("o1", "c1", List.of(item));
        final float total = 10.5f;
        assertEquals(total, order.getTotal());
        final OrderItem itemTwo = new OrderItem("i2", "Item 2", 10.5f, "p2", 2f);
        final Order orderTwo = new Order("o1", "c1", List.of(item, itemTwo));
        final float totalTwo = 31.5f;
        assertEquals(totalTwo, orderTwo.getTotal());
    }

    @Test
    void shouldThrowErrorWhenQuantityIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 10.5f, "p1", null));
    }

    @Test
    void shouldThrowErrorWhenQuantityIsLtZero() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 10.5f, "p1", -1f));
    }
    @Test
    void shouldThrowErrorWhenQuantityIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 10.5f, "p1", 0f));
    }

    @Test
    void shouldThrowErrorWhenPriceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", null, "p1", 1f));
    }

    @Test
    void shouldThrowErrorWhenPriceIsLtZero() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", -1f, "p1", 1f));
    }

    @Test
    void shouldThrowErrorWhenPriceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 0f, "p1", 1f));
    }

    @Test
    void shouldThrowErrorWhenItemIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(null, "o1", 10.5f, "p1", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("", "o1", 10.5f, "p1", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemIdIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("  ", "o1", 10.5f, "p1", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", null, 10.5f, "p1", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "", 10.5f, "p1", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "  ", 10.5f, "p1", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemProductIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 10.5f, null, 2f));
    }

    @Test
    void shouldThrowErrorWhenItemProductIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 10.5f, "", 2f));
    }

    @Test
    void shouldThrowErrorWhenItemProductIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("o1", "o1", 10.5f, "  ", 2f));
    }

    @Test
    void shouldCreateOrderItem() {
        assertInstanceOf(OrderItem.class, new OrderItem("o1", "o1", 10.5f, "p1", 2f));
    }

}

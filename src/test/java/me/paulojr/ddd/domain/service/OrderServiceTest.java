package me.paulojr.ddd.domain.service;

import me.paulojr.ddd.domain.checkout.service.OrderService;
import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.checkout.entity.Order;
import me.paulojr.ddd.domain.checkout.entity.OrderItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class OrderServiceTest {

    private static OrderService orderService;

    @BeforeAll
    static void beforeAll() {
        orderService = new OrderService();
    }

    @Test
    void shouldGetTotalOfAllOrders() {
        final OrderItem item1 = new OrderItem("i1", "Item 1", 100f, "p1", 1f);
        final OrderItem item2 = new OrderItem("i2", "Item 2", 200f, "p2", 2f);
        final Order order1 = new Order("o1", "c1", List.of(item1));
        final Order order2 = new Order("o2", "c2", List.of(item2));
        final Order order3 = new Order("o3", "c3", List.of(item1, item2));
        final float total = orderService.total(List.of(order1, order2, order3));
        assertEquals(1000, total);
    }

    @Test
    void shouldPlaceAnOrder() {
        final Customer customer = new Customer("c1", "Customer 1");
        final OrderItem orderItem1 = new OrderItem("oi1", "Item 1", 10f, "p1", 1f);
        final Order order = orderService.placeOrder(customer, List.of(orderItem1));
        assertEquals(5f, customer.getRewardPoints());
        assertEquals(10f, order.getTotal());
    }

    @Test
    void shouldErrorPlaceAnOrderWithNullItems() {
        final Customer customer = new Customer("c1", "Customer 1");
        assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(customer, null));
    }

    @Test
    void shouldErrorPlaceAnOrderWithNoItems() {
        final Customer customer = new Customer("c1", "Customer 1");
        assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(customer, Collections.emptyList()));
    }
}

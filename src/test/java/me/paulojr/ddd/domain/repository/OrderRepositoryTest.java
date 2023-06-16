package me.paulojr.ddd.domain.repository;

import me.paulojr.ddd.domain.checkout.repository.OrderRepository;
import me.paulojr.ddd.domain.customer.repository.CustomerRepository;
import me.paulojr.ddd.domain.customer.vo.Address;
import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.checkout.entity.Order;
import me.paulojr.ddd.domain.checkout.entity.OrderItem;
import me.paulojr.ddd.domain.product.entity.Product;
import me.paulojr.ddd.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldCreateNewOrder() {
        final Customer customer = new Customer("123", "Customer 1");
        customer.changeAddress(new Address("Street 1", 1, "1232456789", "City"));
        customer.activate();
        this.customerRepository.create(customer).join();
        final Product product = new Product("123", "Product 1", 10f);
        this.productRepository.create(product).join();
        final OrderItem orderItem = new OrderItem("123", product.getName(), product.getPrice(), product.getId(), 2f);
        final Order order = new Order("123", customer.getId(), List.of(orderItem));
        this.orderRepository.create(order).join();
        final Order retrieved = this.orderRepository.find("123").join();
        assertEquals("123", retrieved.getId());
        assertEquals("123", retrieved.getCustomerId());
        assertEquals(20f, retrieved.getTotal());
        assertEquals(orderItem, retrieved.getItems().get(0));
    }

    @Test
    void shouldUpdateOrder() {
        final Customer customer = new Customer("123", "Customer 1");
        customer.changeAddress(new Address("Street 1", 1, "1232456789", "City"));
        customer.activate();
        this.customerRepository.create(customer).join();
        final Product product = new Product("123", "Product 1", 10f);
        this.productRepository.create(product).join();
        final OrderItem orderItem = new OrderItem("123", product.getName(), product.getPrice(), product.getId(), 2f);
        final OrderItem orderItem2 = new OrderItem("321", product.getName(), product.getPrice(), product.getId(), 4f);
        final Order order = new Order("123", customer.getId(), List.of(orderItem));
        this.orderRepository.create(order).join();
        final Order retrieved = this.orderRepository.find("123").join();
        retrieved.getItems().add(orderItem2);
        this.orderRepository.update(retrieved).join();
        final Order updated = this.orderRepository.find("123").join();
        assertEquals("123", updated.getId());
        assertEquals("123", updated.getCustomerId());
        assertEquals(60, updated.getTotal());
        assertEquals(orderItem, updated.getItems().get(0));
        assertEquals(orderItem2, updated.getItems().get(1));
    }


    @Test
    void shouldFindOrder() {
        final Customer customer = new Customer("123", "Customer 1");
        customer.changeAddress(new Address("Street 1", 1, "1232456789", "City"));
        customer.activate();
        this.customerRepository.create(customer).join();
        final Product product = new Product("123", "Product 1", 10f);
        this.productRepository.create(product).join();
        final OrderItem orderItem = new OrderItem("123", product.getName(), product.getPrice(), product.getId(), 2f);
        final Order order = new Order("123", customer.getId(), List.of(orderItem));
        this.orderRepository.create(order).join();
        final Order retrieved = this.orderRepository.find("123").join();
        assertEquals("123", retrieved.getId());
        assertEquals("123", retrieved.getCustomerId());
        assertEquals(20f, retrieved.getTotal());
        assertEquals(orderItem, retrieved.getItems().get(0));
    }

    @Test
    void shouldFindAllOrder() {
        final Customer customer = new Customer("123", "Customer 1");
        customer.changeAddress(new Address("Street 1", 1, "1232456789", "City"));
        customer.activate();
        this.customerRepository.create(customer).join();
        final Product product = new Product("123", "Product 1", 10f);
        this.productRepository.create(product).join();
        final OrderItem orderItem = new OrderItem("123", product.getName(), product.getPrice(), product.getId(), 2f);
        final Order order = new Order("123", customer.getId(), List.of(orderItem));
        this.orderRepository.create(order).join();
        final OrderItem orderItem2 = new OrderItem("321", product.getName(), product.getPrice(), product.getId(), 4f);
        final Order order2 = new Order("321", customer.getId(), List.of(orderItem2));
        this.orderRepository.create(order2).join();
        final List<Order> retrieved = this.orderRepository.findAll().join();
        final Order saved1 = retrieved.stream().filter(order1 -> order1.getId().equals("123")).findFirst().orElse(null);
        final Order saved2 = retrieved.stream().filter(order1 -> order1.getId().equals("321")).findFirst().orElse(null);
        assertEquals("123", Objects.requireNonNull(saved1).getId());
        assertEquals("123", saved1.getCustomerId());
        assertEquals(20f, saved1.getTotal());
        assertEquals(orderItem, saved1.getItems().get(0));
        assertEquals("321", Objects.requireNonNull(saved2).getId());
        assertEquals("123", saved2.getCustomerId());
        assertEquals(40f, saved2.getTotal());
        assertEquals(orderItem2, saved2.getItems().get(0));
    }

}

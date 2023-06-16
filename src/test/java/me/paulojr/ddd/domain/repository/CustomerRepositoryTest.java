package me.paulojr.ddd.domain.repository;

import me.paulojr.ddd.domain.customer.repository.CustomerRepository;
import me.paulojr.ddd.domain.customer.vo.Address;
import me.paulojr.ddd.domain.customer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindcustomer() {
        Customer customer = new Customer("1", "customer Name");
        customer.changeAddress(new Address("Rua 1", 10, "123456789", "Sapiranga"));
        customer.activate();
        customer.addReawardPoints(2f);
        customerRepository.create(customer).join();
        CompletableFuture<Customer> saved = customerRepository.find("1");
        Customer find = saved.join();
        assertEquals("1", find.getId());
        assertEquals("customer Name", find.getName());
        assertTrue(find.isActive());
        assertEquals(customer.getAddress(), find.getAddress());
        assertEquals(2f, find.getRewardPoints());
    }

    @Test
    public void shouldUpdatecustomer() {
        Customer Customer = new Customer("1", "customer 1");
        customerRepository.create(Customer).join();
        CompletableFuture<Customer> saved = customerRepository.find("1");
        Customer = saved.join();
        Customer.changeName("Update customer");
        customerRepository.update(Customer).join();
        Customer = customerRepository.find("1").join();
        assertEquals("1", Customer.getId());
        assertEquals("Update Customer", Customer.getName());
    }

    @Test
    public void shouldFindAllCustomers() {
        Customer customerOne = new Customer("1", "customer 1");
        Customer customerTwo = new Customer("2", "customer 2");
        customerRepository.create(customerOne).join();
        customerRepository.create(customerTwo).join();
        CompletableFuture<List<Customer>> saved = customerRepository.findAll();
        List<Customer> ls = saved.join();
        List<Customer> prods = List.of(customerOne, customerTwo);

        final List<Customer> list = ls.stream().filter(customer -> prods.stream().noneMatch(s -> Objects.equals(s.getId(), customer.getId()) &&
                Objects.equals(s.getAddress(), customer.getAddress()) &&
                s.getName().equals(customer.getName()) &&
                Objects.equals(s.getRewardPoints(), customer.getRewardPoints()))).collect(Collectors.toList());
        assertEquals(0, list.size());
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = new Customer("1", "Customer Name");
        customer.changeAddress(new Address("Rua 1", 10, "123456789", "Sapiranga"));
        customer.activate();
        customer.addReawardPoints(2f);
        customerRepository.create(customer).join();
        CompletableFuture<Customer> saved = customerRepository.find("1");
        Customer find = saved.join();
        assertEquals("1", find.getId());
        assertEquals("Customer Name", find.getName());
        assertTrue(find.isActive());
        assertEquals(customer.getAddress(), find.getAddress());
        assertEquals(2f, find.getRewardPoints());
    }

}

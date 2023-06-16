package me.paulojr.ddd.domain.entity.customer;

import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.customer.vo.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CustomerTest {

    @Test
    void shouldThrowErrorWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, "Nome Completo"));
    }

    @Test
    void shouldThrowErrorWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", null));
    }

    @Test
    void shouldThrowErrorWhenIdIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("  ", "Nome Completo"));
    }

    @Test
    void shouldThrowErrorWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "Nome Completo"));
    }

    @Test
    void shouldThrowErrorWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("1", ""));
    }

    @Test
    void shouldThrowCreatecustomer() {
        assertInstanceOf(Customer.class, new Customer("123", "Nome Completo"));
    }


    @Test
    void shouldThrowErrorWhenChangeNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("1", "Nome Completo").changeName(""));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("1", "Nome Completo").changeName(null));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("1", "Nome Completo").changeName(" "));
    }

    @Test
    void shouldChangeName() {
        final Customer customer = new Customer("123", "Nome anterior");
        customer.changeName("Nome Completo");
        assertEquals("Nome Completo", customer.getName());
    }

    @Test
    void shouldThrowErrorWhenAddressNullAndActivate() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("1", "Name").activate());
    }

    @Test
    void shouldActivate() {
        final Customer customer = new Customer("123", "Nome Completo");
        final Address address = new Address("Rua", 1, "123456-123", "Cidade");
        customer.changeAddress(address);
        customer.activate();
        assertTrue(customer.isActive());
    }

    @Test
    void shouldDeactivate() {
        final Customer customer = new Customer("123", "Nome Completo");
        final Address address = new Address("Rua", 1, "123456-123", "Cidade");
        customer.changeAddress(address);
        customer.activate();
        customer.deactivate();
        assertFalse(customer.isActive());
    }

    @Test
    void shouldAddRewardPoints() {
        final Customer customer = new Customer("c1", "customer 1");
        assertEquals(0, customer.getRewardPoints());
        customer.addReawardPoints(5f);
        assertEquals(5, customer.getRewardPoints());
        customer.addReawardPoints(25f);
        assertEquals(30f, customer.getRewardPoints());
    }

}

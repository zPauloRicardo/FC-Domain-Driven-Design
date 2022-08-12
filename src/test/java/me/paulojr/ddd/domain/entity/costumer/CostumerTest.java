package me.paulojr.ddd.domain.entity.costumer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CostumerTest {

    @Test
    void shouldThrowErrorWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer(null, "Nome Completo"));
    }

    @Test
    void shouldThrowErrorWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("", null));
    }

    @Test
    void shouldThrowErrorWhenIdIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("  ", "Nome Completo"));
    }

    @Test
    void shouldThrowErrorWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("", "Nome Completo"));
    }

    @Test
    void shouldThrowErrorWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("1", ""));
    }

    @Test
    void shouldThrowCreateCostumer() {
        assertInstanceOf(Costumer.class, new Costumer("123", "Nome Completo"));
    }


    @Test
    void shouldThrowErrorWhenChangeNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("1", "Nome Completo").changeName(""));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("1", "Nome Completo").changeName(null));
    }

    @Test
    void shouldThrowErrorWhenChangeNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("1", "Nome Completo").changeName(" "));
    }

    @Test
    void shouldChangeName() {
        final Costumer costumer = new Costumer("123", "Nome anterior");
        costumer.changeName("Nome Completo");
        assertEquals("Nome Completo", costumer.getName());
    }

    @Test
    void shouldThrowErrorWhenAddressNullAndActivate() {
        assertThrows(IllegalArgumentException.class, () -> new Costumer("1", "Name").activate());
    }

    @Test
    void shouldActivate() {
        final Costumer costumer = new Costumer("123", "Nome Completo");
        final Address address = new Address("Rua", 1, "123456-123", "Cidade");
        costumer.setAddress(address);
        costumer.activate();
        assertTrue(costumer.isActive());
    }

    @Test
    void shouldDeactivate() {
        final Costumer costumer = new Costumer("123", "Nome Completo");
        final Address address = new Address("Rua", 1, "123456-123", "Cidade");
        costumer.setAddress(address);
        costumer.activate();
        costumer.deactivate();
        assertFalse(costumer.isActive());
    }

    @Test
    void shouldAddRewardPoints() {
        final Costumer costumer = new Costumer("c1", "Costumer 1");
        assertEquals(0, costumer.getRewardPoints());
        costumer.addReawardPoints(5f);
        assertEquals(5, costumer.getRewardPoints());
        costumer.addReawardPoints(25f);
        assertEquals(30f, costumer.getRewardPoints());
    }

}

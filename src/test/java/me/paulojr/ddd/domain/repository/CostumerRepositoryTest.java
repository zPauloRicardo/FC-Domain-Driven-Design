package me.paulojr.ddd.domain.repository;

import me.paulojr.ddd.domain.entity.costumer.Address;
import me.paulojr.ddd.domain.entity.costumer.Costumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest

public class CostumerRepositoryTest {

    @Autowired
    private CostumerRepository costumerRepository;

    @Test
    public void shouldFindCostumer() {
        Costumer costumer = new Costumer("1", "costumer Name");
        costumer.changeAddress(new Address("Rua 1", 10, "123456789", "Sapiranga"));
        costumer.activate();
        costumer.addReawardPoints(2f);
        costumerRepository.create(costumer).join();
        CompletableFuture<Costumer> saved = costumerRepository.find("1");
        Costumer find = saved.join();
        assertEquals("1", find.getId());
        assertEquals("costumer Name", find.getName());
        assertTrue(find.isActive());
        assertEquals(costumer.getAddress(), find.getAddress());
        assertEquals(2f, find.getRewardPoints());
    }

    @Test
    public void shouldUpdateCostumer() {
        Costumer Costumer = new Costumer("1", "costumer 1");
        costumerRepository.create(Costumer).join();
        CompletableFuture<Costumer> saved = costumerRepository.find("1");
        Costumer = saved.join();
        Costumer.changeName("Update Costumer");
        costumerRepository.update(Costumer).join();
        Costumer = costumerRepository.find("1").join();
        assertEquals("1", Costumer.getId());
        assertEquals("Update Costumer", Costumer.getName());
    }

    @Test
    public void shouldFindAllCostumers() {
        Costumer costumerOne = new Costumer("1", "costumer 1");
        Costumer costumerTwo = new Costumer("2", "costumer 2");
        costumerRepository.create(costumerOne).join();
        costumerRepository.create(costumerTwo).join();
        CompletableFuture<List<Costumer>> saved = costumerRepository.findAll();
        List<Costumer> ls = saved.join();
        List<Costumer> prods = List.of(costumerOne, costumerTwo);

        final List<Costumer> list = ls.stream().filter(costumer -> prods.stream().noneMatch(s -> Objects.equals(s.getId(), costumer.getId()) &&
                Objects.equals(s.getAddress(),costumer.getAddress()) &&
                s.getName().equals(costumer.getName()) &&
                Objects.equals(s.getRewardPoints(), costumer.getRewardPoints()))).collect(Collectors.toList());
        assertEquals(0, list.size());
    }

    @Test
    public void shouldCreateCostumer() {
        Costumer costumer = new Costumer("1", "Costumer Name");
        costumer.changeAddress(new Address("Rua 1", 10, "123456789", "Sapiranga"));
        costumer.activate();
        costumer.addReawardPoints(2f);
        costumerRepository.create(costumer).join();
        CompletableFuture<Costumer> saved = costumerRepository.find("1");
        Costumer find = saved.join();
        assertEquals("1", find.getId());
        assertEquals("Costumer Name", find.getName());
        assertTrue(find.isActive());
        assertEquals(costumer.getAddress(), find.getAddress());
        assertEquals(2f, find.getRewardPoints());
    }

}

package me.paulojr.ddd.infrastructure.repository;

import me.paulojr.ddd.domain.entity.costumer.Address;
import me.paulojr.ddd.domain.entity.costumer.Costumer;
import me.paulojr.ddd.domain.repository.CostumerRepository;
import me.paulojr.ddd.infrastructure.database.model.CostumerModel;
import me.paulojr.ddd.infrastructure.repository.jpa.CostumerRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class CostumerRepositoryImpl implements CostumerRepository {

    private final CostumerRepositoryJpa costumerRepositoryJpa;

    @Autowired
    public CostumerRepositoryImpl(CostumerRepositoryJpa costumerRepositoryJpa) {
        this.costumerRepositoryJpa = costumerRepositoryJpa;
    }

    @Override
    public CompletableFuture<Void> create(Costumer entity) {
        return CompletableFuture.runAsync(() -> this.costumerRepositoryJpa.save(new CostumerModel(entity)));
    }

    @Override
    public CompletableFuture<Void> update(Costumer entity) {
        return CompletableFuture.runAsync(() -> this.costumerRepositoryJpa.save(new CostumerModel(entity)));
    }

    @Override
    public CompletableFuture<Costumer> find(String id) {
        return CompletableFuture.supplyAsync(() -> this.fromModel(this.costumerRepositoryJpa.findById(id).orElse(null)));
    }

    @Override
    public CompletableFuture<List<Costumer>> findAll() {
        return CompletableFuture.supplyAsync(() -> this.costumerRepositoryJpa.findAll().stream().map(this::fromModel).collect(Collectors.toList()));
    }

    private Costumer fromModel(CostumerModel entity) {
        final Address address = entity != null && entity.getStreet() != null ? new Address(entity.getStreet(), entity.getNumber(), entity.getZip(), entity.getCity()) : null;
        final Costumer costumer = new Costumer(entity.getId(), entity.getName());
        costumer.changeAddress(address);
        if(entity.isActive()) costumer.activate();
        costumer.addReawardPoints(entity.getRewardPoints());
        return costumer;
    }

}

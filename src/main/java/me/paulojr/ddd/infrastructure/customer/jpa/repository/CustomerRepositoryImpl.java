package me.paulojr.ddd.infrastructure.customer.jpa.repository;

import me.paulojr.ddd.domain.customer.vo.Address;
import me.paulojr.ddd.domain.customer.entity.Customer;
import me.paulojr.ddd.domain.customer.repository.CustomerRepository;
import me.paulojr.ddd.infrastructure.customer.jpa.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerRepositoryJpa customerRepositoryJpa;

    @Autowired
    public CustomerRepositoryImpl(CustomerRepositoryJpa customerRepositoryJpa) {
        this.customerRepositoryJpa = customerRepositoryJpa;
    }

    @Override
    public CompletableFuture<Void> create(Customer entity) {
        return CompletableFuture.runAsync(() -> this.customerRepositoryJpa.save(new CustomerModel(entity)));
    }

    @Override
    public CompletableFuture<Void> update(Customer entity) {
        return CompletableFuture.runAsync(() -> this.customerRepositoryJpa.save(new CustomerModel(entity)));
    }

    @Override
    public CompletableFuture<Customer> find(String id) {
        return CompletableFuture.supplyAsync(() -> this.fromModel(Objects.requireNonNull(this.customerRepositoryJpa.findById(id).orElse(null))));
    }

    @Override
    public CompletableFuture<List<Customer>> findAll() {
        return CompletableFuture.supplyAsync(() -> this.customerRepositoryJpa.findAll().stream().map(this::fromModel).collect(Collectors.toList()));
    }

    private Customer fromModel(CustomerModel entity) {
        final Address address = entity != null && entity.getStreet() != null ? new Address(entity.getStreet(), entity.getNumber(), entity.getZip(), entity.getCity()) : null;
        final Customer customer = new Customer(Objects.requireNonNull(entity).getId(), entity.getName());
        customer.changeAddress(address);
        if(entity.isActive()) customer.activate();
        customer.addReawardPoints(entity.getRewardPoints());
        return customer;
    }

}

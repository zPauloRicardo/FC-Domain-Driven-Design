package me.paulojr.ddd.infrastructure.customer.jpa.repository;


import me.paulojr.ddd.infrastructure.customer.jpa.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryJpa extends JpaRepository<CustomerModel, String> {
}

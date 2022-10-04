package me.paulojr.ddd.infrastructure.repository.jpa;

import me.paulojr.ddd.infrastructure.database.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderModel, String> {
}

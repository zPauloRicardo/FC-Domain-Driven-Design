package me.paulojr.ddd.infrastructure.order.jpa.repository;

import me.paulojr.ddd.infrastructure.order.jpa.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderModel, String> {
}

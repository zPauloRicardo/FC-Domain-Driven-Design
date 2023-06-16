package me.paulojr.ddd.infrastructure.product.jpa.repository;


import me.paulojr.ddd.infrastructure.product.jpa.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa  extends JpaRepository<ProductModel, String> {
}

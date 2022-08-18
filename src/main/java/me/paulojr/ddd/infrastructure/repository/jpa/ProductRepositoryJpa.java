package me.paulojr.ddd.infrastructure.repository.jpa;


import me.paulojr.ddd.infrastructure.database.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa  extends JpaRepository<ProductModel, String> {
}

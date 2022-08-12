package me.paulojr.ddd.infrastructure.repository;

import me.paulojr.ddd.domain.entity.product.Product;
import me.paulojr.ddd.domain.repository.ProductRepository;
import me.paulojr.ddd.infrastructure.database.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductRepositoryJpa productRepository;

    @Autowired
    public ProductRepositoryImpl(ProductRepositoryJpa productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CompletableFuture<Void> create(Product entity) {
        return CompletableFuture.runAsync(() -> this.productRepository.save(new ProductModel(entity)));
    }

    @Override
    public CompletableFuture<Void> update(Product entity) {
        return CompletableFuture.runAsync(() -> this.productRepository.save(new ProductModel(entity)));
    }

    @Override
    public CompletableFuture<Product> find(String id) {
        return CompletableFuture.supplyAsync(() -> this.fromModel(this.productRepository.findById(id).orElse(null)));
    }

    @Override
    public CompletableFuture<List<Product>> findAll() {
        return CompletableFuture.supplyAsync(() -> this.productRepository.findAll().stream().map(this::fromModel).collect(Collectors.toList()));
    }

    private Product fromModel(ProductModel productModel) {
        return productModel != null ? new Product(productModel.getId(), productModel.getName(), productModel.getPrice()) : null;
    }

}

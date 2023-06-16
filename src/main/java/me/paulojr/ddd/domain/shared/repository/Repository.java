package me.paulojr.ddd.domain.shared.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Repository<T> {

    CompletableFuture<Void> create(T entity);
    CompletableFuture<Void> update(T entity);
    CompletableFuture<T> find(String id);
    CompletableFuture<List<T>> findAll();

}

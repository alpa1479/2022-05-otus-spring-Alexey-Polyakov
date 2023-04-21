package ru.otus.spring.hw07.core.operations;

import java.util.List;
import java.util.Optional;

public interface CrudOperations<T> {

    List<T> findAll();

    Optional<T> findById(long id);

    T save(T object);

    void deleteById(long id);
}

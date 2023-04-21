package ru.otus.spring.hw06.core.operations;

import java.util.List;

public interface CrudOperations<T> {

    List<T> findAll();

    T findById(long id);

    T create(T object);

    T update(T object);

    void deleteById(long id);
}

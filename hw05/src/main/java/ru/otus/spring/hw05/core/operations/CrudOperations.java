package ru.otus.spring.hw05.core.operations;

import java.util.List;

public interface CrudOperations<T> {

    List<T> findAll();

    T findById(long id);

    long create(T object);

    void update(T object);

    void deleteById(long id);
}

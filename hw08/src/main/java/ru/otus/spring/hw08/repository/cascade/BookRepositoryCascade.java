package ru.otus.spring.hw08.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

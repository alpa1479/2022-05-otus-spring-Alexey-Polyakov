package ru.otus.spring.hw17.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

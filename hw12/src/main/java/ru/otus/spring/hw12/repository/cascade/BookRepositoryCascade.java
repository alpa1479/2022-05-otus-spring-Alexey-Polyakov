package ru.otus.spring.hw12.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

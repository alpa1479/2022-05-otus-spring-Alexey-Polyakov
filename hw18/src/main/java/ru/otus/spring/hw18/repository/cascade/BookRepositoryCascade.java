package ru.otus.spring.hw18.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

package ru.otus.spring.hw13.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

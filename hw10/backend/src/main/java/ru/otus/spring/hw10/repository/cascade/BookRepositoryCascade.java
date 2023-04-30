package ru.otus.spring.hw10.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

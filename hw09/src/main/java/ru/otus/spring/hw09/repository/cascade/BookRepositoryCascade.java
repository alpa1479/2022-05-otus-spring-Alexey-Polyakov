package ru.otus.spring.hw09.repository.cascade;

public interface BookRepositoryCascade {

    void cascadeDeleteById(String id);
}

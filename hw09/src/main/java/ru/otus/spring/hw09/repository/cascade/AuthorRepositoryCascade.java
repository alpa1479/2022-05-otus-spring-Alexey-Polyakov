package ru.otus.spring.hw09.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

package ru.otus.spring.hw08.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

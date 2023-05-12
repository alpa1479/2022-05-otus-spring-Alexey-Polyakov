package ru.otus.spring.hw17.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

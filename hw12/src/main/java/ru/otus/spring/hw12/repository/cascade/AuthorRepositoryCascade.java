package ru.otus.spring.hw12.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

package ru.otus.spring.hw13.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

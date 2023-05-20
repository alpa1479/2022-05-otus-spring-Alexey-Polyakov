package ru.otus.spring.hw18.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

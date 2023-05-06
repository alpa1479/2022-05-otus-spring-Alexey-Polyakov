package ru.otus.spring.hw10.repository.cascade;

public interface AuthorRepositoryCascade {

    void cascadeDeleteById(String id);
}

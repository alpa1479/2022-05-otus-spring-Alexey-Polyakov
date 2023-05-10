package ru.otus.spring.hw12.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

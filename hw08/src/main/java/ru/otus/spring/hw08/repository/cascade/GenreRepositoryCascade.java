package ru.otus.spring.hw08.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

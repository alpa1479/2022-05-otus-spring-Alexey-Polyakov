package ru.otus.spring.hw17.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

package ru.otus.spring.hw13.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

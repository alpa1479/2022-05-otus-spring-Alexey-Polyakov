package ru.otus.spring.hw18.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

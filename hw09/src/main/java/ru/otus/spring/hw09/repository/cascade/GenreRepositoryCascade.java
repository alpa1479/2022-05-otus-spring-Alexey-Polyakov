package ru.otus.spring.hw09.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

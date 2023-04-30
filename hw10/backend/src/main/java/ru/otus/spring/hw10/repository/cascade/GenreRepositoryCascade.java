package ru.otus.spring.hw10.repository.cascade;

public interface GenreRepositoryCascade {

    void cascadeDeleteById(String id);
}

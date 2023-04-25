package ru.otus.spring.hw08.repository.cascade;

public interface CommentRepositoryCascade {

    void cascadeDeleteById(String id);
}

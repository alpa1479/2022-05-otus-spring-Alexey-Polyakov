package ru.otus.spring.hw10.repository.cascade;

public interface CommentRepositoryCascade {

    void cascadeDeleteById(String id);

    void cascadeDeleteByBookId(String bookId);
}

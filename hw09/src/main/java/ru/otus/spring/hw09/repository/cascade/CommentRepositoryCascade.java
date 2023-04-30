package ru.otus.spring.hw09.repository.cascade;

public interface CommentRepositoryCascade {

    void cascadeDeleteById(String id);

    void cascadeDeleteByBookId(String bookId);
}

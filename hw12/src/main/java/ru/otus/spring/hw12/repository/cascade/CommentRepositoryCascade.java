package ru.otus.spring.hw12.repository.cascade;

public interface CommentRepositoryCascade {

    void cascadeDeleteById(String id);

    void cascadeDeleteByBookId(String bookId);
}

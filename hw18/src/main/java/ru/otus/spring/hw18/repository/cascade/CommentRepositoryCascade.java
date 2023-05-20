package ru.otus.spring.hw18.repository.cascade;

public interface CommentRepositoryCascade {

    void cascadeDeleteById(String id);

    void cascadeDeleteByBookId(String bookId);
}

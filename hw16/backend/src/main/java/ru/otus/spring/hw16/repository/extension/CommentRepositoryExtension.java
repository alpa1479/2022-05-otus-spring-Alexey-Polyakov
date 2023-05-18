package ru.otus.spring.hw16.repository.extension;

public interface CommentRepositoryExtension {

    void deleteById(String id);

    void deleteByBookId(String bookId);
}

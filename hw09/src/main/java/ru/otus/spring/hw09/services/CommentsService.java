package ru.otus.spring.hw09.services;

import ru.otus.spring.hw09.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    List<Comment> findAll();

    List<Comment> findCommentsByBookId(String id);

    Optional<Comment> findById(String id);

    Comment save(Comment comment);

    void deleteById(String id);

    void deleteAllByBookId(String bookId);
}

package ru.otus.spring.hw10.services;

import ru.otus.spring.hw10.domain.Comment;
import ru.otus.spring.hw10.dto.SaveCommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    List<Comment> findAll();

    Optional<Comment> findById(String id);

    List<Comment> findByBookId(String id);

    Comment save(Comment comment);

    void save(SaveCommentDto saveCommentDto);

    void deleteById(String id);

    void deleteAllByBookId(String bookId);
}

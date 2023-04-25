package ru.otus.spring.hw08.services;

import ru.otus.spring.hw08.core.operations.CrudOperations;
import ru.otus.spring.hw08.domain.Comment;

import java.util.List;

public interface CommentsService extends CrudOperations<Comment> {

    List<Comment> findCommentsByBookId(String id);
}

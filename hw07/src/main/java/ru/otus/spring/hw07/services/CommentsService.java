package ru.otus.spring.hw07.services;

import ru.otus.spring.hw07.core.operations.CrudOperations;
import ru.otus.spring.hw07.domain.Comment;

import java.util.List;

public interface CommentsService extends CrudOperations<Comment> {

    List<Comment> findCommentsByBookId(long id);
}

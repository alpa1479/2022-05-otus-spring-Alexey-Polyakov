package ru.otus.spring.hw06.services;

import ru.otus.spring.hw06.core.operations.CrudOperations;
import ru.otus.spring.hw06.domain.Comment;

import java.util.List;

public interface CommentsService extends CrudOperations<Comment> {

    List<Comment> findCommentsByBookId(long id);
}

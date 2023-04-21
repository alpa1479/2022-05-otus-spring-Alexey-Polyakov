package ru.otus.spring.hw06.services.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw06.domain.Book;
import ru.otus.spring.hw06.domain.Comment;
import ru.otus.spring.hw06.repository.BookRepository;
import ru.otus.spring.hw06.repository.CommentRepository;
import ru.otus.spring.hw06.services.CommentsService;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            List<Comment> comments = book.getComments();
            Hibernate.initialize(comments);
            return comments;
        }
        return emptyList();
    }

    @Override
    @Transactional
    public Comment findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment create(Comment comment) {
        return commentRepository.create(comment);
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        return commentRepository.update(comment);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}

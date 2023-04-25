package ru.otus.spring.hw09.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw09.domain.Book;
import ru.otus.spring.hw09.domain.Comment;
import ru.otus.spring.hw09.repository.BookRepository;
import ru.otus.spring.hw09.repository.CommentRepository;
import ru.otus.spring.hw09.repository.cascade.CommentRepositoryCascade;
import ru.otus.spring.hw09.services.CommentsService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final CommentRepositoryCascade commentRepositoryCascade;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findCommentsByBookId(String id) {
        return bookRepository.findById(id).map(Book::getComments).orElseGet(Collections::emptyList);
    }

    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(String id) {
        commentRepositoryCascade.cascadeDeleteById(id);
    }

    @Override
    public void deleteAllByBookId(String bookId) {
        commentRepositoryCascade.cascadeDeleteByBookId(bookId);
    }
}

package ru.otus.spring.hw07.services.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.domain.Comment;
import ru.otus.spring.hw07.repository.BookRepository;
import ru.otus.spring.hw07.repository.CommentRepository;
import ru.otus.spring.hw07.services.CommentsService;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            List<Comment> comments = book.get().getComments();
            Hibernate.initialize(comments);
            return comments;
        }
        return emptyList();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}

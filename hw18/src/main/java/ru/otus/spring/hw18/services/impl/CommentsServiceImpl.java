package ru.otus.spring.hw18.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.domain.Book;
import ru.otus.spring.hw18.domain.Comment;
import ru.otus.spring.hw18.dto.SaveCommentDto;
import ru.otus.spring.hw18.repository.BookRepository;
import ru.otus.spring.hw18.repository.CommentRepository;
import ru.otus.spring.hw18.repository.cascade.CommentRepositoryCascade;
import ru.otus.spring.hw18.services.CommentsService;

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
    @CircuitBreaker(name = "mongodb")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public List<Comment> findByBookId(String id) {
        return bookRepository.findById(id).map(Book::getComments).orElseGet(Collections::emptyList);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void save(SaveCommentDto saveCommentDto) {
        bookRepository.findById(saveCommentDto.getBookId()).ifPresent(book -> saveToBook(saveCommentDto, book));
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void deleteById(String id) {
        commentRepositoryCascade.cascadeDeleteById(id);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void deleteAllByBookId(String bookId) {
        commentRepositoryCascade.cascadeDeleteByBookId(bookId);
    }

    private void saveToBook(SaveCommentDto saveCommentDto, Book book) {
        String text = saveCommentDto.getText();
        Comment comment = save(new Comment(text, book));
        book.addComment(comment);
        bookRepository.save(book);
    }
}

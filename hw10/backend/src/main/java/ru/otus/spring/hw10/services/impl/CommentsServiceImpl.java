package ru.otus.spring.hw10.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw10.domain.Book;
import ru.otus.spring.hw10.domain.Comment;
import ru.otus.spring.hw10.dto.SaveCommentDto;
import ru.otus.spring.hw10.repository.BookRepository;
import ru.otus.spring.hw10.repository.CommentRepository;
import ru.otus.spring.hw10.repository.cascade.CommentRepositoryCascade;
import ru.otus.spring.hw10.services.CommentsService;

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
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByBookId(String id) {
        return bookRepository.findById(id).map(Book::getComments).orElseGet(Collections::emptyList);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void save(SaveCommentDto saveCommentDto) {
        bookRepository.findById(saveCommentDto.getBookId()).ifPresent(book -> saveToBook(saveCommentDto, book));
    }

    @Override
    public void deleteById(String id) {
        commentRepositoryCascade.cascadeDeleteById(id);
    }

    @Override
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

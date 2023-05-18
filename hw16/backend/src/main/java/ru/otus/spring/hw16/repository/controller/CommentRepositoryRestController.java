package ru.otus.spring.hw16.repository.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.repository.BookRepository;
import ru.otus.spring.hw16.repository.CommentRepository;

@RepositoryRestController
@RequiredArgsConstructor
public class CommentRepositoryRestController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/books/{id}/comments")
    public ResponseEntity<?> addCommentToBook(@PathVariable String id, @RequestBody Comment commentToSave) {
        Comment comment = commentRepository.save(commentToSave);
        bookRepository.findById(id).ifPresent(book -> {
            book.addComment(comment);
            bookRepository.save(book);
        });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/books/{id}/comments")
    public ResponseEntity<?> deleteByBookId(@PathVariable String id) {
        commentRepository.deleteByBookId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

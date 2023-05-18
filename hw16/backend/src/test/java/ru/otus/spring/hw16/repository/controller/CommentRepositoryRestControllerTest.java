package ru.otus.spring.hw16.repository.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.repository.BookRepository;
import ru.otus.spring.hw16.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentRepositoryRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SpyBean
    private BookRepository bookRepository;

    @SpyBean
    private CommentRepository commentRepository;

    @Test
    @DisplayName("should add a comment to book")
    void shouldAddCommentToBook() {
        String bookId = bookRepository.findByTitle("Summer Catch").map(Book::getId).orElse(null);
        Comment commentToSave = new Comment("Test comment");

        ResponseEntity<Void> response = restTemplate.postForEntity("/api/books/{id}/comments", commentToSave, Void.class, bookId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(commentRepository).save(any());
        verify(bookRepository).findById(anyString());
        verify(bookRepository).save(any());
    }

    @Test
    @DisplayName("should delete all comments for book")
    void shouldDeleteAllCommentsForBook() {
        String bookId = bookRepository.findByTitle("Summer Catch").map(Book::getId).orElse(null);

        ResponseEntity<Void> response = restTemplate.exchange("/api/books/{id}/comments", HttpMethod.DELETE, null, Void.class, bookId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(commentRepository).deleteByBookId(bookId);
    }
}

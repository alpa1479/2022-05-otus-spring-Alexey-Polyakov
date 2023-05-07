package ru.otus.spring.hw12.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw12.domain.Book;
import ru.otus.spring.hw12.domain.Comment;
import ru.otus.spring.hw12.domain.Genre;
import ru.otus.spring.hw12.repository.BookRepository;
import ru.otus.spring.hw12.repository.CommentRepository;
import ru.otus.spring.hw12.repository.cascade.BookRepositoryCascade;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given book repository")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepositoryCascade bookRepositoryCascade;

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() {
        assertThat(bookRepository.findAll())
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("comments", "authors")
                .isEqualTo(getExpectedBooks());
    }

    @Test
    @DisplayName("should return book by id")
    void shouldReturnBookById() {
        Book expectedBook = bookRepository.findAll().stream().findFirst().orElseThrow();
        assertThat(bookRepository.findById(expectedBook.getId()))
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("should create book")
    void shouldCreateBook() {
        Book newBook = new Book(null, "new book title");
        Book createdBook = bookRepository.save(newBook);

        assertThat(createdBook).matches(book -> book.getTitle().equals(newBook.getTitle()));
        bookRepository.deleteById(createdBook.getId());
    }

    @Test
    @DisplayName("should update book")
    void shouldUpdateBook() {
        Book newBook = new Book(null, "new book title");
        Book createdBook = bookRepository.save(newBook);

        createdBook.setTitle("changed book title");
        Book updatedBook = bookRepository.save(createdBook);

        assertThat(updatedBook).matches(book -> book.getTitle().equals("changed book title"));
        bookRepository.deleteById(updatedBook.getId());
    }


    @Test
    @DisplayName("should delete book by id")
    void shouldDeleteBookById() {
        Book newBook = new Book(null, "new book title");
        Book createdBook = bookRepository.save(newBook);
        Comment newComment = new Comment(null, "text", createdBook);
        commentRepository.save(newComment);

        String bookId = createdBook.getId();
        bookRepositoryCascade.cascadeDeleteById(bookId);

        assertThat(bookRepository.findById(bookId)).isNotPresent();
        assertThat(commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getBook().getId().equals(bookId))
                .toList()).isEmpty();
    }

    List<Book> getExpectedBooks() {
        return List.of(
                new Book("The Oblong Box", new Genre("drama")),
                new Book("Skin Deep", new Genre("horror")),
                new Book("Summer Catch", new Genre("thriller"))
        );
    }
}
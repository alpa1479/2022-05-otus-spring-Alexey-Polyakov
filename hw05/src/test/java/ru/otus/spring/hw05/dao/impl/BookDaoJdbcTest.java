package ru.otus.spring.hw05.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.hw05.domain.Book;
import ru.otus.spring.hw05.domain.Genre;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@JdbcTest
@Import({BookDaoJdbc.class})
@DisplayName("Given book dao jdbc")
class BookDaoJdbcTest {

    @MockBean
    private AuthorDaoJdbc authorDaoJdbc;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @BeforeEach
    void setup() {
        when(authorDaoJdbc.findByIds(anyCollection())).thenReturn(emptyList());
    }

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() {
        assertThat(bookDaoJdbc.findAll()).isEqualTo(getExpectedBooks());
    }

    @Test
    @DisplayName("should return book by id")
    void shouldReturnBookById() {
        assertThat(bookDaoJdbc.findById(1)).isEqualTo(getExpectedBook());
    }

    @Test
    @DisplayName("should create book")
    void shouldCreateBook() {
        Book newBook = new Book("new book title", new Genre(3));
        long createdBookId = bookDaoJdbc.create(newBook);
        assertThat(bookDaoJdbc.findById(createdBookId)).matches(book -> book.getId() == createdBookId && book.getTitle().equals(newBook.getTitle()));
    }

    @Test
    @DisplayName("should update book")
    void shouldUpdateBook() {
        Book bookToUpdate = new Book(1, "update book name", new Genre(3, "thriller"));
        bookDaoJdbc.update(bookToUpdate);
        assertThat(bookDaoJdbc.findById(bookToUpdate.getId())).isEqualTo(bookToUpdate);
    }

    @Test
    @DisplayName("should delete book by id")
    void shouldDeleteBookById() {
        bookDaoJdbc.deleteById(1);
        assertThatThrownBy(() -> bookDaoJdbc.findById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    Book getExpectedBook() {
        return new Book(1, "The Oblong Box", new Genre(1, "drama"));
    }

    List<Book> getExpectedBooks() {
        return of(
                getExpectedBook(),
                new Book(2, "Skin Deep", new Genre(2, "horror")),
                new Book(3, "Summer Catch", new Genre(3, "thriller")),
                new Book(4, "The Missionaries", new Genre(4, "action")),
                new Book(5, "9 Star Hotel (Malon 9 Kochavim)", new Genre(5, "detective")),
                new Book(6, "Street Kings", new Genre(6, "sci-fi")),
                new Book(7, "Squeeze", new Genre(7, "crime")),
                new Book(8, "Old San Francisco", new Genre(8, "documentary")),
                new Book(9, "Pauly Shore Is Dead", new Genre(9, "adventure")),
                new Book(10, "Wayward Bus", new Genre(10, "fantasy")),
                new Book(11, "Brimstone and Treacle", new Genre(6, "sci-fi")),
                new Book(12, "The Walking Stick", new Genre(4, "action")),
                new Book(13, "Eight Below", new Genre(3, "thriller")),
                new Book(14, "Riders of the Purple Sage", new Genre(8, "documentary")),
                new Book(15, "Harry and Tonto", new Genre(10, "fantasy"))
        );
    }

}
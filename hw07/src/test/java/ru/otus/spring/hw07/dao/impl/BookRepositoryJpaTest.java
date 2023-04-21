package ru.otus.spring.hw07.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.domain.Comment;
import ru.otus.spring.hw07.domain.Genre;
import ru.otus.spring.hw07.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Given book repository jpa")
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() {
        assertThat(bookRepository.findAll()).isEqualTo(getExpectedBooks());
    }

    @Test
    @DisplayName("should return book by id")
    void shouldReturnBookById() {
        assertThat(bookRepository.findById(1L)).isPresent().hasValue(getExpectedBook());
    }

    @Test
    @DisplayName("should return comments by book id")
    void shouldReturnCommentsByBookId() {
        Optional<Book> book = bookRepository.findById(1L);
        assertThat(book.map(Book::getComments).orElse(emptyList())).hasSameElementsAs(getExpectedComments());
    }

    @Test
    @DisplayName("should create book")
    void shouldCreateBook() {
        Book newBook = new Book("new book title", new Genre(3L));
        Book createdBook = bookRepository.save(newBook);
        testEntityManager.flush();
        assertThat(createdBook).matches(book -> book.getTitle().equals(newBook.getTitle()));
    }

    @Test
    @DisplayName("should update book")
    void shouldUpdateBook() {
        Book bookToUpdate = new Book(1L, "update book name", new Genre(3L, "thriller"));
        bookRepository.save(bookToUpdate);
        testEntityManager.flush();
        testEntityManager.clear();
        assertThat(bookRepository.findById(bookToUpdate.getId())).isPresent().hasValue(bookToUpdate);
    }

    @Test
    @DisplayName("should delete book by id")
    void shouldDeleteBookById() {
        bookRepository.deleteById(1L);
        testEntityManager.flush();
        testEntityManager.clear();
        assertThat(bookRepository.findById(1L)).isNotPresent();
    }

    Book getExpectedBook() {
        return new Book(1L, "The Oblong Box", new Genre(1L, "drama"));
    }

    List<Book> getExpectedBooks() {
        return of(
                getExpectedBook(),
                new Book(2L, "Skin Deep", new Genre(2L, "horror")),
                new Book(3L, "Summer Catch", new Genre(3L, "thriller")),
                new Book(4L, "The Missionaries", new Genre(4L, "action")),
                new Book(5L, "9 Star Hotel (Malon 9 Kochavim)", new Genre(5L, "detective")),
                new Book(6L, "Street Kings", new Genre(6L, "sci-fi")),
                new Book(7L, "Squeeze", new Genre(7L, "crime")),
                new Book(8L, "Old San Francisco", new Genre(8L, "documentary")),
                new Book(9L, "Pauly Shore Is Dead", new Genre(9L, "adventure")),
                new Book(10L, "Wayward Bus", new Genre(10L, "fantasy")),
                new Book(11L, "Brimstone and Treacle", new Genre(6L, "sci-fi")),
                new Book(12L, "The Walking Stick", new Genre(4L, "action")),
                new Book(13L, "Eight Below", new Genre(3L, "thriller")),
                new Book(14L, "Riders of the Purple Sage", new Genre(8L, "documentary")),
                new Book(15L, "Harry and Tonto", new Genre(10L, "fantasy"))
        );
    }

    List<Comment> getExpectedComments() {
        return of(
                new Comment(1L, "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."),
                new Comment(2L, "Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst."),
                new Comment(3L, "Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.")
        );
    }

}
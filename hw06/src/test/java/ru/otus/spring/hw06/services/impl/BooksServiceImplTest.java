package ru.otus.spring.hw06.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw06.domain.Book;
import ru.otus.spring.hw06.domain.Genre;
import ru.otus.spring.hw06.repository.BookRepository;
import ru.otus.spring.hw06.services.BooksService;

import static org.mockito.Mockito.verify;

@DisplayName("Given books service impl")
@SpringBootTest(classes = {BooksServiceImpl.class})
class BooksServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BooksService booksService;

    @Test
    @DisplayName("should call BookRepository to find all books")
    void shouldCallBookRepositoryToFindAllBooks() {
        booksService.findAll();
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("should call BookRepository to find book by id")
    void shouldCallBookRepositoryToFindBookById() {
        booksService.findById(1);
        verify(bookRepository).findById(1);
    }

    @Test
    @DisplayName("should call BookRepository to create book")
    void shouldCallBookRepositoryToCreateBook() {
        Book book = new Book("book", new Genre(1L));
        booksService.create(book);
        verify(bookRepository).create(book);
    }

    @Test
    @DisplayName("should call BookRepository to update book")
    void shouldCallBookRepositoryToUpdateBook() {
        Book book = new Book(1L, "book", new Genre(1L));
        booksService.update(book);
        verify(bookRepository).update(book);
    }

    @Test
    @DisplayName("should call BookRepository to delete book by id")
    void shouldCallBookRepositoryToDeleteBookById() {
        booksService.deleteById(1);
        verify(bookRepository).deleteById(1);
    }
}
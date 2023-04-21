package ru.otus.spring.hw07.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.domain.Genre;
import ru.otus.spring.hw07.repository.BookRepository;
import ru.otus.spring.hw07.services.BooksService;

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
        booksService.findById(1L);
        verify(bookRepository).findById(1L);
    }

    @Test
    @DisplayName("should call BookRepository to save book")
    void shouldCallBookRepositoryToCreateBook() {
        Book book = new Book("book", new Genre(1L));
        booksService.save(book);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("should call BookRepository to delete book by id")
    void shouldCallBookRepositoryToDeleteBookById() {
        booksService.deleteById(1L);
        verify(bookRepository).deleteById(1L);
    }
}
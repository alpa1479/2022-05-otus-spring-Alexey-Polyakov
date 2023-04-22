package ru.otus.spring.hw08.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.domain.Genre;
import ru.otus.spring.hw08.repository.BookRepository;
import ru.otus.spring.hw08.repository.cascade.BookRepositoryCascade;
import ru.otus.spring.hw08.services.BooksService;

import static org.mockito.Mockito.verify;

@DisplayName("Given books service impl")
@SpringBootTest(classes = {BooksServiceImpl.class})
class BooksServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookRepositoryCascade bookRepositoryCascade;

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
        booksService.findById("1");
        verify(bookRepository).findById("1");
    }

    @Test
    @DisplayName("should call BookRepository to save book")
    void shouldCallBookRepositoryToCreateBook() {
        Book book = new Book("book", new Genre("1"));
        booksService.save(book);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("should call BookRepositoryCascade to delete book by id")
    void shouldCallBookRepositoryToDeleteBookById() {
        booksService.deleteById("1");
        verify(bookRepositoryCascade).cascadeDeleteById("1");
    }
}
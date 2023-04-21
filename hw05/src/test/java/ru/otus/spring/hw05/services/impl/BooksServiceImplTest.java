package ru.otus.spring.hw05.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw05.core.transactionmanager.TransactionManagerSpring;
import ru.otus.spring.hw05.dao.BookDao;
import ru.otus.spring.hw05.domain.Book;
import ru.otus.spring.hw05.domain.Genre;
import ru.otus.spring.hw05.services.BooksService;

import static org.mockito.Mockito.verify;

@DisplayName("Given books service impl")
@SpringBootTest(classes = {BooksServiceImpl.class, TransactionManagerSpring.class})
class BooksServiceImplTest {

    @MockBean
    private BookDao bookDao;

    @Autowired
    private BooksService booksService;

    @Test
    @DisplayName("should call BookDao to find all books")
    void shouldCallBookDaoToFindAllBooks() {
        booksService.findAll();
        verify(bookDao).findAll();
    }

    @Test
    @DisplayName("should call BookDao to find book by id")
    void shouldCallBookDaoToFindBookById() {
        booksService.findById(1);
        verify(bookDao).findById(1);
    }

    @Test
    @DisplayName("should call BookDao to create book")
    void shouldCallBookDaoToCreateBook() {
        Book book = new Book("book", new Genre(1));
        booksService.create(book);
        verify(bookDao).create(book);
    }

    @Test
    @DisplayName("should call BookDao to update book")
    void shouldCallBookDaoToUpdateBook() {
        Book book = new Book(1, "book", new Genre(1));
        booksService.update(book);
        verify(bookDao).update(book);
    }

    @Test
    @DisplayName("should call BookDao to delete book by id")
    void shouldCallBookDaoToDeleteBookById() {
        booksService.deleteById(1);
        verify(bookDao).deleteById(1);
    }
}
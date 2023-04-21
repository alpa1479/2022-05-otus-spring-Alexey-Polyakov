package ru.otus.spring.hw05.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw05.core.transactionmanager.TransactionManagerSpring;
import ru.otus.spring.hw05.dao.AuthorDao;
import ru.otus.spring.hw05.domain.Author;
import ru.otus.spring.hw05.services.AuthorsService;

import static org.mockito.Mockito.verify;

@DisplayName("Given authors service impl")
@SpringBootTest(classes = {AuthorsServiceImpl.class, TransactionManagerSpring.class})
class AuthorsServiceImplTest {

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private AuthorsService authorsService;

    @Test
    @DisplayName("should call AuthorDao to find all authors")
    void shouldCallAuthorDaoToFindAllAuthors() {
        authorsService.findAll();
        verify(authorDao).findAll();
    }

    @Test
    @DisplayName("should call AuthorDao to find author by id")
    void shouldCallAuthorDaoToFindAuthorById() {
        authorsService.findById(1);
        verify(authorDao).findById(1);
    }

    @Test
    @DisplayName("should call AuthorDao to create author")
    void shouldCallAuthorDaoToCreateAuthor() {
        Author author = new Author("author");
        authorsService.create(author);
        verify(authorDao).create(author);
    }

    @Test
    @DisplayName("should call AuthorDao to update author")
    void shouldCallAuthorDaoToUpdateAuthor() {
        Author author = new Author(1, "author");
        authorsService.update(author);
        verify(authorDao).update(author);
    }

    @Test
    @DisplayName("should call AuthorDao to delete author by id")
    void shouldCallAuthorDaoToDeleteAuthorById() {
        authorsService.deleteById(1);
        verify(authorDao).deleteById(1);
    }
}
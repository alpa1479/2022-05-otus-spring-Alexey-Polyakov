package ru.otus.spring.hw07.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw07.domain.Author;
import ru.otus.spring.hw07.repository.AuthorRepository;
import ru.otus.spring.hw07.services.AuthorsService;

import static org.mockito.Mockito.verify;

@DisplayName("Given authors service impl")
@SpringBootTest(classes = {AuthorsServiceImpl.class})
class AuthorsServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorsService authorsService;

    @Test
    @DisplayName("should call AuthorRepository to find all authors")
    void shouldCallAuthorRepositoryToFindAllAuthors() {
        authorsService.findAll();
        verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("should call AuthorRepository to find author by id")
    void shouldCallAuthorRepositoryToFindAuthorById() {
        authorsService.findById(1L);
        verify(authorRepository).findById(1L);
    }

    @Test
    @DisplayName("should call AuthorRepository to save author")
    void shouldCallAuthorRepositoryToCreateAuthor() {
        Author author = new Author("author");
        authorsService.save(author);
        verify(authorRepository).save(author);
    }

    @Test
    @DisplayName("should call AuthorRepository to delete author by id")
    void shouldCallAuthorRepositoryToDeleteAuthorById() {
        authorsService.deleteById(1L);
        verify(authorRepository).deleteById(1L);
    }
}
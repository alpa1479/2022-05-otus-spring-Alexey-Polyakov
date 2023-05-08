package ru.otus.spring.hw13.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw13.converters.Converter;
import ru.otus.spring.hw13.domain.Author;
import ru.otus.spring.hw13.dto.AuthorDto;
import ru.otus.spring.hw13.repository.AuthorRepository;
import ru.otus.spring.hw13.repository.cascade.AuthorRepositoryCascade;
import ru.otus.spring.hw13.services.AuthorsService;

import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Given authors service impl")
@SpringBootTest(classes = {AuthorsServiceImpl.class})
class AuthorsServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private AuthorRepositoryCascade authorRepositoryCascade;

    @MockBean
    private Converter<Author, AuthorDto> toAuthorDtoConverter;

    @MockBean
    private Converter<AuthorDto, Author> toAuthorConverter;

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
        authorsService.findById("1");
        verify(authorRepository).findById("1");
    }

    @Test
    @DisplayName("should call AuthorRepository to save author")
    void shouldCallAuthorRepositoryToCreateAuthor() {
        Author author = new Author("author");
        AuthorDto authorDto = new AuthorDto(null, "author");

        when(toAuthorConverter.convert(authorDto)).thenReturn(of(author));
        authorsService.save(authorDto);

        verify(authorRepository).save(author);
    }

    @Test
    @DisplayName("should call AuthorRepositoryCascade to delete author by id")
    void shouldCallAuthorRepositoryToDeleteAuthorById() {
        authorsService.deleteById("1");
        verify(authorRepositoryCascade).cascadeDeleteById("1");
    }
}
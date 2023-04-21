package ru.otus.spring.hw06.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw06.domain.Author;
import ru.otus.spring.hw06.repository.AuthorRepository;
import ru.otus.spring.hw06.repository.impl.AuthorRepositoryJpa;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorRepositoryJpa.class})
@DisplayName("Given author repository jpa")
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() {
        assertThat(authorRepository.findAll()).isEqualTo(getExpectedAuthors());
    }

    @Test
    @DisplayName("should return all authors by ids")
    void shouldReturnAuthorsByIds() {
        assertThat(authorRepository.findByIds(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L))).isEqualTo(getExpectedAuthors());
    }

    @Test
    @DisplayName("should return author by id")
    void shouldReturnAuthorById() {
        assertThat(authorRepository.findById(1)).isEqualTo(getExpectedAuthor());
    }

    @Test
    @DisplayName("should create author")
    void shouldCreateAuthor() {
        Author newAuthor = new Author("new author name");
        Author createdAuthor = authorRepository.create(newAuthor);
        assertThat(createdAuthor).matches(author -> author.getName().equals(newAuthor.getName()));
    }

    @Test
    @DisplayName("should update author")
    void shouldUpdateAuthor() {
        Author authorToUpdate = new Author(1L, "updated author name");
        authorRepository.update(authorToUpdate);
        assertThat(authorRepository.findById(authorToUpdate.getId())).isEqualTo(authorToUpdate);
    }

    @Test
    @DisplayName("should delete author by id")
    void shouldDeleteAuthorById() {
        authorRepository.deleteById(1);
        assertThat(authorRepository.findById(1)).isNull();
    }

    Author getExpectedAuthor() {
        return new Author(1L, "Thomas Artis");
    }

    List<Author> getExpectedAuthors() {
        return List.of(
                getExpectedAuthor(),
                new Author(2L, "Rania Martinez"),
                new Author(3L, "Nathanial Snugg"),
                new Author(4L, "Kathryn Armatys"),
                new Author(5L, "Row Le Sarr"),
                new Author(6L, "Krystal Ryves"),
                new Author(7L, "Pat Cambridge")
        );
    }
}
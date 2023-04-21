package ru.otus.spring.hw07.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.hw07.domain.Author;
import ru.otus.spring.hw07.repository.AuthorRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "logging.level.ROOT= WARN",
        "logging.level.org.springframework.test.context.transaction= INFO",
        "logging.level.org.hibernate.SQL= DEBUG"
})
@DisplayName("Given author repository jpa")
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() {
        assertThat(authorRepository.findAll()).isEqualTo(getExpectedAuthors());
    }

    @Test
    @DisplayName("should return author by id")
    void shouldReturnAuthorById() {
        assertThat(authorRepository.findById(1L)).isPresent().hasValue(getExpectedAuthor());
    }

    @Test
    @DisplayName("should create author")
    void shouldCreateAuthor() {
        Author newAuthor = new Author("new author name");
        Author createdAuthor = authorRepository.save(newAuthor);
        testEntityManager.flush();
        assertThat(createdAuthor).matches(author -> author.getName().equals(newAuthor.getName()));
    }

    @Test
    @DisplayName("should update author")
    void shouldUpdateAuthor() {
        Author authorToUpdate = new Author(1L, "updated author name");
        authorRepository.save(authorToUpdate);
        testEntityManager.flush();
        testEntityManager.clear();
        assertThat(authorRepository.findById(authorToUpdate.getId())).isPresent().hasValue(authorToUpdate);
    }

    @Test
    @DisplayName("should delete author by id")
    void shouldDeleteAuthorById() {
        authorRepository.deleteById(1L);
        testEntityManager.flush();
        testEntityManager.clear();
        assertThat(authorRepository.findById(1L)).isNotPresent();
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
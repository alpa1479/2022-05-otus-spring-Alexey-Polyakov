package ru.otus.spring.hw05.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.hw05.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@JdbcTest
@Import({AuthorDaoJdbc.class})
@DisplayName("Given author dao jdbc")
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() {
        assertThat(authorDaoJdbc.findAll()).isEqualTo(getExpectedAuthors());
    }

    @Test
    @DisplayName("should return all authors by ids")
    void shouldReturnAuthorsByIds() {
        assertThat(authorDaoJdbc.findByIds(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L))).isEqualTo(getExpectedAuthors());
    }

    @Test
    @DisplayName("should return author by id")
    void shouldReturnAuthorById() {
        assertThat(authorDaoJdbc.findById(1)).isEqualTo(getExpectedAuthor());
    }

    @Test
    @DisplayName("should create author")
    void shouldCreateAuthor() {
        Author newAuthor = new Author("new author name");
        long createdAuthorId = authorDaoJdbc.create(newAuthor);
        assertThat(authorDaoJdbc.findById(createdAuthorId)).matches(author -> author.getId() == createdAuthorId && author.getName().equals(newAuthor.getName()));
    }

    @Test
    @DisplayName("should update author")
    void shouldUpdateAuthor() {
        Author authorToUpdate = new Author(1, "updated author name");
        authorDaoJdbc.update(authorToUpdate);
        assertThat(authorDaoJdbc.findById(authorToUpdate.getId())).isEqualTo(authorToUpdate);
    }

    @Test
    @DisplayName("should delete author by id")
    void shouldDeleteAuthorById() {
        authorDaoJdbc.deleteById(1);
        assertThatThrownBy(() -> authorDaoJdbc.findById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    Author getExpectedAuthor() {
        return new Author(1, "Thomas Artis");
    }

    List<Author> getExpectedAuthors() {
        return List.of(
                getExpectedAuthor(),
                new Author(2, "Rania Martinez"),
                new Author(3, "Nathanial Snugg"),
                new Author(4, "Kathryn Armatys"),
                new Author(5, "Row Le Sarr"),
                new Author(6, "Krystal Ryves"),
                new Author(7, "Pat Cambridge")
        );
    }
}
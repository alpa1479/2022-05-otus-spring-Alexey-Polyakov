package ru.otus.spring.hw05.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.hw05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import({GenreDaoJdbc.class})
@DisplayName("Given genre dao jdbc")
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() {
        assertThat(genreDaoJdbc.findAll()).isEqualTo(getExpectedGenres());
    }

    @Test
    @DisplayName("should return genre by id")
    void shouldReturnGenreById() {
        assertThat(genreDaoJdbc.findById(1)).isEqualTo(getExpectedGenre());
    }

    @Test
    @DisplayName("should create genre")
    void shouldCreateGenre() {
        Genre newGenre = new Genre("new genre name");
        long createdGenreId = genreDaoJdbc.create(newGenre);
        assertThat(genreDaoJdbc.findById(createdGenreId)).matches(genre -> genre.getId() == createdGenreId && genre.getName().equals(newGenre.getName()));
    }

    @Test
    @DisplayName("should update genre")
    void shouldUpdateGenre() {
        Genre genreToUpdate = new Genre(1, "updated genre");
        genreDaoJdbc.update(genreToUpdate);
        assertThat(genreDaoJdbc.findById(genreToUpdate.getId())).isEqualTo(genreToUpdate);
    }

    @Test
    @DisplayName("should delete genre by id")
    void shouldDeleteGenreById() {
        genreDaoJdbc.deleteById(1);
        assertThatThrownBy(() -> genreDaoJdbc.findById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    Genre getExpectedGenre() {
        return new Genre(1, "drama");
    }

    List<Genre> getExpectedGenres() {
        return List.of(
                getExpectedGenre(),
                new Genre(2, "horror"),
                new Genre(3, "thriller"),
                new Genre(4, "action"),
                new Genre(5, "detective"),
                new Genre(6, "sci-fi"),
                new Genre(7, "crime"),
                new Genre(8, "documentary"),
                new Genre(9, "adventure"),
                new Genre(10, "fantasy")
        );
    }
}
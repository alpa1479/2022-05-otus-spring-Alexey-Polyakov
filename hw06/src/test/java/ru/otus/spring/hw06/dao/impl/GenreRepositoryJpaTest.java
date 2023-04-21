package ru.otus.spring.hw06.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw06.domain.Genre;
import ru.otus.spring.hw06.repository.GenreRepository;
import ru.otus.spring.hw06.repository.impl.GenreRepositoryJpa;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({GenreRepositoryJpa.class})
@DisplayName("Given genre repository jpa")
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() {
        assertThat(genreRepository.findAll()).isEqualTo(getExpectedGenres());
    }

    @Test
    @DisplayName("should return genre by id")
    void shouldReturnGenreById() {
        assertThat(genreRepository.findById(1)).isEqualTo(getExpectedGenre());
    }

    @Test
    @DisplayName("should create genre")
    void shouldCreateGenre() {
        Genre newGenre = new Genre("new genre name");
        Genre createdGenre = genreRepository.create(newGenre);
        assertThat(createdGenre).matches(genre -> genre.getName().equals(newGenre.getName()));
    }

    @Test
    @DisplayName("should update genre")
    void shouldUpdateGenre() {
        Genre genreToUpdate = new Genre(1L, "updated genre");
        genreRepository.update(genreToUpdate);
        assertThat(genreRepository.findById(genreToUpdate.getId())).isEqualTo(genreToUpdate);
    }

    @Test
    @DisplayName("should delete genre by id")
    void shouldDeleteGenreById() {
        genreRepository.deleteById(1);
        assertThat(genreRepository.findById(1)).isNull();
    }

    Genre getExpectedGenre() {
        return new Genre(1L, "drama");
    }

    List<Genre> getExpectedGenres() {
        return List.of(
                getExpectedGenre(),
                new Genre(2L, "horror"),
                new Genre(3L, "thriller"),
                new Genre(4L, "action"),
                new Genre(5L, "detective"),
                new Genre(6L, "sci-fi"),
                new Genre(7L, "crime"),
                new Genre(8L, "documentary"),
                new Genre(9L, "adventure"),
                new Genre(10L, "fantasy")
        );
    }
}
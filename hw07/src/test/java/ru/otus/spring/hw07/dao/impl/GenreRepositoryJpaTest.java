package ru.otus.spring.hw07.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.hw07.domain.Genre;
import ru.otus.spring.hw07.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Given genre repository jpa")
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() {
        assertThat(genreRepository.findAll()).isEqualTo(getExpectedGenres());
    }

    @Test
    @DisplayName("should return all genres by ids")
    void shouldReturnAllGenresByIds() {
        List<Long> ids = List.of(1L, 2L, 3L);
        List<Genre> expectedGenres = getExpectedGenres().stream().filter(genre -> ids.contains(genre.getId())).toList();
        assertThat(genreRepository.findAllById(ids)).isEqualTo(expectedGenres);
    }

    @Test
    @DisplayName("should return genre by id")
    void shouldReturnGenreById() {
        assertThat(genreRepository.findById(1L)).isPresent().hasValue(getExpectedGenre());
    }

    @Test
    @DisplayName("should create genre")
    void shouldCreateGenre() {
        Genre newGenre = new Genre("new genre name");
        Genre createdGenre = genreRepository.save(newGenre);
        testEntityManager.flush();
        assertThat(createdGenre).matches(genre -> genre.getName().equals(newGenre.getName()));
    }

    @Test
    @DisplayName("should update genre")
    void shouldUpdateGenre() {
        Genre genreToUpdate = new Genre(1L, "updated genre");
        genreRepository.save(genreToUpdate);
        testEntityManager.flush();
        testEntityManager.clear();
        assertThat(genreRepository.findById(genreToUpdate.getId())).isPresent().hasValue(genreToUpdate);
    }

    @Test
    @DisplayName("should delete genre by id")
    void shouldDeleteGenreById() {
        genreRepository.deleteById(1L);
        testEntityManager.flush();
        testEntityManager.clear();
        assertThat(genreRepository.findById(1L)).isNotPresent();
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
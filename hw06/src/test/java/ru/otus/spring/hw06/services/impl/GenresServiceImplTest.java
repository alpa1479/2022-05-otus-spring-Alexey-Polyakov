package ru.otus.spring.hw06.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw06.domain.Genre;
import ru.otus.spring.hw06.repository.GenreRepository;
import ru.otus.spring.hw06.services.GenresService;

import static org.mockito.Mockito.verify;

@DisplayName("Given genres service impl")
@SpringBootTest(classes = {GenresServiceImpl.class})
class GenresServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenresService genresService;

    @Test
    @DisplayName("should call GenreRepository to find all genres")
    void shouldCallGenreRepositoryToFindAllGenres() {
        genresService.findAll();
        verify(genreRepository).findAll();
    }

    @Test
    @DisplayName("should call GenreRepository to find genre by id")
    void shouldCallGenreRepositoryToFindGenreById() {
        genresService.findById(1);
        verify(genreRepository).findById(1);
    }

    @Test
    @DisplayName("should call GenreRepository to create genre")
    void shouldCallGenreRepositoryToCreateGenre() {
        Genre genre = new Genre("genre");
        genresService.create(genre);
        verify(genreRepository).create(genre);
    }

    @Test
    @DisplayName("should call GenreRepository to update genre")
    void shouldCallGenreRepositoryToUpdateGenre() {
        Genre genre = new Genre(1L, "genre");
        genresService.update(genre);
        verify(genreRepository).update(genre);
    }

    @Test
    @DisplayName("should call GenreRepository to delete genre by id")
    void shouldCallGenreRepositoryToDeleteGenreById() {
        genresService.deleteById(1);
        verify(genreRepository).deleteById(1);
    }
}
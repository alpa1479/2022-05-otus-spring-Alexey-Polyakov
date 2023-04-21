package ru.otus.spring.hw07.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw07.domain.Genre;
import ru.otus.spring.hw07.repository.GenreRepository;
import ru.otus.spring.hw07.services.GenresService;

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
        genresService.findById(1L);
        verify(genreRepository).findById(1L);
    }

    @Test
    @DisplayName("should call GenreRepository to save genre")
    void shouldCallGenreRepositoryToCreateGenre() {
        Genre genre = new Genre("genre");
        genresService.save(genre);
        verify(genreRepository).save(genre);
    }

    @Test
    @DisplayName("should call GenreRepository to delete genre by id")
    void shouldCallGenreRepositoryToDeleteGenreById() {
        genresService.deleteById(1L);
        verify(genreRepository).deleteById(1L);
    }
}
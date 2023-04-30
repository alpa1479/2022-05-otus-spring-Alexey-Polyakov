package ru.otus.spring.hw09.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw09.converters.Converter;
import ru.otus.spring.hw09.domain.Genre;
import ru.otus.spring.hw09.dto.GenreDto;
import ru.otus.spring.hw09.repository.GenreRepository;
import ru.otus.spring.hw09.repository.cascade.GenreRepositoryCascade;
import ru.otus.spring.hw09.services.GenresService;

import static org.mockito.Mockito.verify;

@DisplayName("Given genres service impl")
@SpringBootTest(classes = {GenresServiceImpl.class})
class GenresServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private GenreRepositoryCascade genreRepositoryCascade;

    @MockBean
    private Converter<Genre, GenreDto> toGenreDtoConverter;

    @MockBean
    private Converter<GenreDto, Genre> toGenreConverter;

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
        genresService.findById("1");
        verify(genreRepository).findById("1");
    }

    @Test
    @DisplayName("should call GenreRepository to save genre")
    void shouldCallGenreRepositoryToCreateGenre() {
        Genre genre = new Genre("genre");
        genresService.save(genre);
        verify(genreRepository).save(genre);
    }

    @Test
    @DisplayName("should call GenreRepositoryCascade to delete genre by id")
    void shouldCallGenreRepositoryToDeleteGenreById() {
        genresService.deleteById("1");
        verify(genreRepositoryCascade).cascadeDeleteById("1");
    }
}
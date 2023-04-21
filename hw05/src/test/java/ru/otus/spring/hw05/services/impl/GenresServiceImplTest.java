package ru.otus.spring.hw05.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw05.core.transactionmanager.TransactionManagerSpring;
import ru.otus.spring.hw05.dao.GenreDao;
import ru.otus.spring.hw05.domain.Genre;
import ru.otus.spring.hw05.services.GenresService;

import static org.mockito.Mockito.verify;

@DisplayName("Given genres service impl")
@SpringBootTest(classes = {GenresServiceImpl.class, TransactionManagerSpring.class})
class GenresServiceImplTest {

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private GenresService genresService;

    @Test
    @DisplayName("should call GenreDao to find all genres")
    void shouldCallGenreDaoToFindAllGenres() {
        genresService.findAll();
        verify(genreDao).findAll();
    }

    @Test
    @DisplayName("should call GenreDao to find genre by id")
    void shouldCallGenreDaoToFindGenreById() {
        genresService.findById(1);
        verify(genreDao).findById(1);
    }

    @Test
    @DisplayName("should call GenreDao to create genre")
    void shouldCallGenreDaoToCreateGenre() {
        Genre genre = new Genre("genre");
        genresService.create(genre);
        verify(genreDao).create(genre);
    }

    @Test
    @DisplayName("should call GenreDao to update genre")
    void shouldCallGenreDaoToUpdateGenre() {
        Genre genre = new Genre(1, "genre");
        genresService.update(genre);
        verify(genreDao).update(genre);
    }

    @Test
    @DisplayName("should call GenreDao to delete genre by id")
    void shouldCallGenreDaoToDeleteGenreById() {
        genresService.deleteById(1);
        verify(genreDao).deleteById(1);
    }
}
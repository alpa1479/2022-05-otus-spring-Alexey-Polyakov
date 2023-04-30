package ru.otus.spring.hw10.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw10.domain.Book;
import ru.otus.spring.hw10.domain.Genre;
import ru.otus.spring.hw10.repository.BookRepository;
import ru.otus.spring.hw10.repository.GenreRepository;
import ru.otus.spring.hw10.repository.cascade.GenreRepositoryCascade;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given genre repository")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepositoryCascade genreRepositoryCascade;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() {
        assertThat(genreRepository.findAll())
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(getExpectedGenres());
    }

    @Test
    @DisplayName("should return genre by id")
    void shouldReturnGenreById() {
        Genre expectedGenre = genreRepository.findAll().stream().findFirst().orElseThrow();
        assertThat(genreRepository.findById(expectedGenre.getId()))
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("should create genre")
    void shouldCreateGenre() {
        Genre newGenre = new Genre("new genre");
        Genre createdGenre = genreRepository.save(newGenre);

        assertThat(createdGenre).matches(author -> author.getName().equals(newGenre.getName()));
        genreRepository.deleteById(createdGenre.getId());
    }

    @Test
    @DisplayName("should update genre")
    void shouldUpdateGenre() {
        Genre newGenre = new Genre("new genre");
        Genre createdGenre = genreRepository.save(newGenre);

        createdGenre.setName("changed genre");
        Genre updatedGenre = genreRepository.save(createdGenre);

        assertThat(updatedGenre).matches(genre -> genre.getName().equals("changed genre"));
        genreRepository.deleteById(updatedGenre.getId());
    }

    @Test
    @DisplayName("should delete genre by id")
    void shouldDeleteGenreById() {
        Genre newGenre = new Genre("new genre");
        Genre createdGenre = genreRepository.save(newGenre);

        Book newBook = new Book(null, "new book title", createdGenre);
        bookRepository.save(newBook);

        String genreId = createdGenre.getId();
        genreRepositoryCascade.cascadeDeleteById(genreId);

        assertThat(genreRepository.findById(genreId)).isNotPresent();
        assertThat(bookRepository.findAll()
                .stream()
                .filter(book -> book.getGenre().getId().equals(genreId))
                .toList()).isEmpty();
    }

    List<Genre> getExpectedGenres() {
        return List.of(
                new Genre("drama"),
                new Genre("horror"),
                new Genre("thriller")
        );
    }
}
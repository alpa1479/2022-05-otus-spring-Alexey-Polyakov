package ru.otus.spring.hw11.repository;

import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Genre;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given genre repository")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongockInitializingBeanRunner mongockInitializingBeanRunner;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() {
        StepVerifier.create(genreRepository.findAll().collectList())
                .assertNext(genres -> assertThat(genres)
                        .usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(getExpectedGenres())
                )
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should return genre by id")
    void shouldReturnGenreById() {
        Genre expectedGenre = genreRepository.findByName("drama").blockFirst();

        StepVerifier.create(genreRepository.findById(expectedGenre.getId()))
                .assertNext(genre ->
                        assertThat(genre)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedGenre))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should create genre")
    void shouldCreateGenre() {
        Genre expectedGenre = genreRepository.save(new Genre("new genre")).block();

        String genreId = expectedGenre.getId();
        StepVerifier.create(genreRepository.findById(genreId))
                .assertNext(genre ->
                        assertThat(genre)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedGenre))
                .expectComplete()
                .verify();

        genreRepository.deleteById(genreId).block();
    }

    @Test
    @DisplayName("should update genre")
    void shouldUpdateGenre() {
        Genre newGenre = genreRepository.save(new Genre("new genre")).block();
        newGenre.setName("Updated genre");
        Genre expectedGenre = genreRepository.save(newGenre).block();

        String genreId = expectedGenre.getId();
        StepVerifier.create(genreRepository.findById(genreId))
                .assertNext(genre ->
                        assertThat(genre)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedGenre))
                .expectComplete()
                .verify();

        genreRepository.deleteById(genreId).block();
    }

    @Test
    @DisplayName("should delete genre by id")
    void shouldDeleteGenreById() {
        String savedGenreId = genreRepository.save(new Genre("new genre")).block().getId();
        Book savedBook = bookRepository.save(new Book(null, "new book title", savedGenreId, emptyList(), emptyList())).block();
        String savedBookId = savedBook.getId();

        StepVerifier.create(genreRepository.findById(savedGenreId))
                .assertNext(genre -> assertThat(genre).isNotNull())
                .expectComplete()
                .verify();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .assertNext(book -> assertThat(book).extracting(Book::getGenreId).isEqualTo(savedGenreId))
                .expectComplete()
                .verify();

        genreRepository.deleteWithRelationsById(savedGenreId).block();

        StepVerifier.create(genreRepository.findById(savedGenreId))
                .expectNextCount(0)
                .expectComplete()
                .verify();

        StepVerifier.create(bookRepository.findAllWithRelations().collectList())
                .assertNext(books -> assertThat(books).extracting(Book::getGenreId).asList().doesNotContain(savedGenreId))
                .expectComplete()
                .verify();

        bookRepository.deleteById(savedBookId).block();
    }

    List<Genre> getExpectedGenres() {
        return List.of(
                new Genre("drama"),
                new Genre("horror"),
                new Genre("thriller")
        );
    }
}
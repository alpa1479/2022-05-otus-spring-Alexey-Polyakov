package ru.otus.spring.hw11.repository;

import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.domain.Book;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given author repository")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongockInitializingBeanRunner mongockInitializingBeanRunner;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() {
        StepVerifier.create(authorRepository.findAll().collectList())
                .assertNext(authors -> assertThat(authors)
                        .usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(getExpectedAuthors())
                )
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should return author by id")
    void shouldReturnAuthorById() {
        Author expectedAuthor = authorRepository.findByName("Thomas Artis").blockFirst();

        StepVerifier.create(authorRepository.findById(expectedAuthor.getId()))
                .assertNext(author ->
                        assertThat(author)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedAuthor))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should create author")
    void shouldCreateAuthor() {
        Author expectedAuthor = authorRepository.save(new Author("Test Author")).block();

        String authorId = expectedAuthor.getId();
        StepVerifier.create(authorRepository.findById(authorId))
                .assertNext(author ->
                        assertThat(author)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedAuthor))
                .expectComplete()
                .verify();

        authorRepository.deleteById(authorId).block();
    }

    @Test
    @DisplayName("should update author")
    void shouldUpdateAuthor() {
        Author newAuthor = authorRepository.save(new Author("Test Author")).block();
        newAuthor.setName("Updated Test Author");
        Author expectedAuthor = authorRepository.save(newAuthor).block();

        String authorId = expectedAuthor.getId();
        StepVerifier.create(authorRepository.findById(authorId))
                .assertNext(author ->
                        assertThat(author)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedAuthor))
                .expectComplete()
                .verify();

        authorRepository.deleteById(authorId).block();
    }

    @Test
    @DisplayName("should delete author by id")
    void shouldDeleteAuthorById() {
        String savedAuthorId = authorRepository.save(new Author("new author name")).block().getId();
        Book savedBook = bookRepository.save(new Book(null, "new book title", null, List.of(savedAuthorId), emptyList())).block();
        String savedBookId = savedBook.getId();

        StepVerifier.create(authorRepository.findById(savedAuthorId))
                .assertNext(author -> assertThat(author).isNotNull())
                .expectComplete()
                .verify();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .assertNext(book -> assertThat(book).extracting(Book::getAuthorIds).asList().contains(savedAuthorId))
                .expectComplete()
                .verify();

        authorRepository.deleteWithRelationsById(savedAuthorId).block();

        StepVerifier.create(authorRepository.findById(savedAuthorId))
                .expectNextCount(0)
                .expectComplete()
                .verify();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .assertNext(book -> assertThat(book).extracting(Book::getAuthorIds).asList().doesNotContain(savedAuthorId))
                .expectComplete()
                .verify();

        bookRepository.deleteById(savedBookId).block();
    }

    Author getExpectedAuthor() {
        return new Author("Thomas Artis");
    }

    List<Author> getExpectedAuthors() {
        return List.of(
                getExpectedAuthor(),
                new Author("Rania Martinez")
        );
    }
}
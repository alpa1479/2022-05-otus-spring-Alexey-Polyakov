package ru.otus.spring.hw10.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw10.domain.Author;
import ru.otus.spring.hw10.domain.Book;
import ru.otus.spring.hw10.repository.AuthorRepository;
import ru.otus.spring.hw10.repository.BookRepository;
import ru.otus.spring.hw10.repository.cascade.AuthorRepositoryCascade;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given author repository")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorRepositoryCascade authorRepositoryCascade;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() {
        assertThat(authorRepository.findAll())
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(getExpectedAuthors());
    }

    @Test
    @DisplayName("should return author by id")
    void shouldReturnAuthorById() {
        Author expectedAuthor = authorRepository.findAll().stream().findFirst().orElseThrow();
        assertThat(authorRepository.findById(expectedAuthor.getId()))
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("should create author")
    void shouldCreateAuthor() {
        Author newAuthor = new Author("new author name");
        Author createdAuthor = authorRepository.save(newAuthor);

        assertThat(createdAuthor).matches(author -> author.getName().equals(newAuthor.getName()));
        authorRepository.deleteById(createdAuthor.getId());
    }

    @Test
    @DisplayName("should update author")
    void shouldUpdateAuthor() {
        Author newAuthor = new Author("new author name");
        Author createdAuthor = authorRepository.save(newAuthor);

        createdAuthor.setName("changed author name");
        Author updatedAuthor = authorRepository.save(createdAuthor);

        assertThat(updatedAuthor).matches(author -> author.getName().equals("changed author name"));
        authorRepository.deleteById(updatedAuthor.getId());
    }

    @Test
    @DisplayName("should delete author by id")
    void shouldDeleteAuthorById() {
        Author newAuthor = new Author("new author name");
        Author createdAuthor = authorRepository.save(newAuthor);
        Book newBook = new Book(null, "new book title", null, List.of(createdAuthor), emptyList());
        Book savedBook = bookRepository.save(newBook);

        String authorId = createdAuthor.getId();
        authorRepositoryCascade.cascadeDeleteById(authorId);

        assertThat(authorRepository.findById(authorId)).isNotPresent();
        assertThat(bookRepository.findAll()
                .stream()
                .filter(book -> book.getAuthors().stream().anyMatch(bookAuthor -> bookAuthor.getId().equals(authorId)))
                .toList()).isEmpty();
        bookRepository.deleteById(savedBook.getId());
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
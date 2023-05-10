package ru.otus.spring.hw11.repository;

import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given book repository")
class BookRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongockInitializingBeanRunner mongockInitializingBeanRunner;

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() {
        StepVerifier.create(bookRepository.findAllWithRelations().collectList())
                .assertNext(books -> assertThat(books)
                        .usingRecursiveComparison()
                        .ignoringCollectionOrder()
                        .ignoringExpectedNullFields()
                        .isEqualTo(getExpectedBooks())
                )
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should return book by id")
    void shouldReturnBookById() {
        Book expectedBook = bookRepository.findByTitle("The Oblong Box").blockFirst();

        StepVerifier.create(bookRepository.findById(expectedBook.getId()))
                .assertNext(book ->
                        assertThat(book)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedBook))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should create book")
    void shouldCreateBook() {
        Book expectedBook = bookRepository.save(new Book("New Book", null)).block();

        String bookId = expectedBook.getId();
        StepVerifier.create(bookRepository.findById(bookId))
                .assertNext(book ->
                        assertThat(book)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedBook))
                .expectComplete()
                .verify();

        bookRepository.deleteById(bookId).block();
    }

    @Test
    @DisplayName("should update book")
    void shouldUpdateBook() {
        Book newBook = bookRepository.save(new Book("New Book", null)).block();
        newBook.setTitle("Updated Title");
        Book expectedBook = bookRepository.save(newBook).block();

        String bookId = expectedBook.getId();
        StepVerifier.create(bookRepository.findById(bookId))
                .assertNext(book ->
                        assertThat(book)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedBook))
                .expectComplete()
                .verify();

        bookRepository.deleteById(bookId).block();
    }


    @Test
    @DisplayName("should delete book by id")
    void shouldDeleteBookById() {
        String savedBookId = bookRepository.save(new Book("New Book", null)).block().getId();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .assertNext(book -> assertThat(book).isNotNull())
                .expectComplete()
                .verify();

        bookRepository.deleteWithRelationsById(savedBookId).block();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .expectNextCount(0)
                .expectComplete()
                .verify();

        StepVerifier.create(commentRepository.findAll().collectList())
                .assertNext(comments -> assertThat(comments).extracting(Comment::getBookId).asList().doesNotContain(savedBookId))
                .expectComplete()
                .verify();

        bookRepository.deleteById(savedBookId).block();
    }

    List<Book> getExpectedBooks() {
        return List.of(
                new Book(null, "The Oblong Box", null, null, null),
                new Book(null, "Skin Deep", null, null, null),
                new Book(null, "Summer Catch", null, null, null)
        );
    }
}
package ru.otus.spring.hw11.configuration.routes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.dto.AuthorDto;
import ru.otus.spring.hw11.dto.BookDto;
import ru.otus.spring.hw11.dto.GenreDto;
import ru.otus.spring.hw11.dto.SaveBookDto;
import ru.otus.spring.hw11.repository.BookRepository;

import java.util.List;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookRoutesConfigurationTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private Converter<Book, BookDto> toBookDtoConverter;

    @MockBean
    private Converter<SaveBookDto, Book> toBookConverter;

    @MockBean
    private Converter<Book, SaveBookDto> toSaveBookDtoConverter;

    @Autowired
    private RouterFunction<ServerResponse> bookRoutes;

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() {
        WebTestClient client = WebTestClient.bindToRouterFunction(bookRoutes).build();
        List<Book> books = List.of(new Book(), new Book());
        List<BookDto> bookDtos = List.of(
                new BookDto("1", "Book 1", new GenreDto("1", "Genre 1"), List.of(new AuthorDto("1", "Author 1")), List.of()),
                new BookDto("2", "Book 2", new GenreDto("2", "Genre 2"), List.of(new AuthorDto("2", "Author 2")), List.of())
        );

        when(bookRepository.findAllWithRelations()).thenReturn(Flux.fromIterable(books));
        when(toBookDtoConverter.convert(books.get(0))).thenReturn(of(bookDtos.get(0)));
        when(toBookDtoConverter.convert(books.get(1))).thenReturn(of(bookDtos.get(1)));

        client.get()
                .uri("/api/v1/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(BookDto.class)
                .isEqualTo(bookDtos);
    }

    @Test
    @DisplayName("should return a book by id")
    void shouldReturnBookById() {
        WebTestClient client = WebTestClient.bindToRouterFunction(bookRoutes).build();
        Book book = new Book();
        SaveBookDto saveBookDto = new SaveBookDto("1", "Book 1", "Genre 1", List.of("Author 1"), List.of("Comment 1"));

        when(bookRepository.findByIdWithRelations(anyString())).thenReturn(Mono.just(book));
        when(toSaveBookDtoConverter.convert(book)).thenReturn(of(saveBookDto));

        client.get()
                .uri("/api/v1/books/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(SaveBookDto.class)
                .isEqualTo(saveBookDto);
    }

    @Test
    @DisplayName("should create a new book")
    void shouldCreateNewBook() {
        WebTestClient client = WebTestClient.bindToRouterFunction(bookRoutes).build();
        Book book = new Book();
        SaveBookDto saveBookDto = new SaveBookDto("1", "Book 1", "Genre 1", List.of("Author 1"), List.of("Comment 1"));

        when(toBookConverter.convert(saveBookDto)).thenReturn(of(book));
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        client.post()
                .uri("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveBookDto))
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("should update a book")
    void shouldUpdateBook() {
        WebTestClient client = WebTestClient.bindToRouterFunction(bookRoutes).build();
        Book book = new Book();
        SaveBookDto saveBookDto = new SaveBookDto("1", "Book 1", "Genre 1", List.of("Author 1"), List.of("Comment 1"));

        when(toBookConverter.convert(saveBookDto)).thenReturn(of(book));
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        client.put()
                .uri("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveBookDto))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("should delete a book by id")
    void shouldDeleteBookById() {
        WebTestClient client = WebTestClient.bindToRouterFunction(bookRoutes).build();

        when(bookRepository.deleteWithRelationsById(anyString())).thenReturn(Mono.empty());

        client.delete()
                .uri("/api/v1/books/1")
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}
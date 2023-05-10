package ru.otus.spring.hw11.configuration.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.dto.BookDto;
import ru.otus.spring.hw11.dto.SaveBookDto;
import ru.otus.spring.hw11.repository.BookRepository;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
public class BookRoutesConfiguration {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookRepository bookRepository,
                                                     Converter<Book, BookDto> toBookDtoConverter,
                                                     Converter<SaveBookDto, Book> toBookConverter,
                                                     Converter<Book, SaveBookDto> toSaveBookDtoConverter) {
        return route()
                .GET("/api/v1/books", request -> bookRepository.findAllWithRelations()
                        .map(toBookDtoConverter::convert)
                        .collectList()
                        .flatMap(books -> ok().bodyValue(books))
                )
                .GET("/api/v1/books/{id}", request -> bookRepository.findByIdWithRelations(request.pathVariable("id"))
                        .map(toSaveBookDtoConverter::convert)
                        .flatMap(book -> ok().bodyValue(book))
                        .switchIfEmpty(notFound().build())
                )
                .POST("api/v1/books", request -> request.bodyToMono(SaveBookDto.class)
                        .map(toBookConverter::convert)
                        .map(Optional::orElseThrow)
                        .flatMap(bookRepository::save)
                        .then(status(HttpStatus.CREATED).build())
                )
                .PUT("api/v1/books", request -> request.bodyToMono(SaveBookDto.class)
                        .map(toBookConverter::convert)
                        .map(Optional::orElseThrow)
                        .flatMap(bookRepository::save)
                        .then(ok().build())
                )
                .DELETE("api/v1/books/{id}", request -> bookRepository.deleteWithRelationsById(request.pathVariable("id"))
                        .then(noContent().build())
                )
                .build();
    }
}
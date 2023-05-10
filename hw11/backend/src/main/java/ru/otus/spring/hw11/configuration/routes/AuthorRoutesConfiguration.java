package ru.otus.spring.hw11.configuration.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.dto.AuthorDto;
import ru.otus.spring.hw11.repository.AuthorRepository;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class AuthorRoutesConfiguration {

    @Bean
    public RouterFunction<ServerResponse> authorRoutes(AuthorRepository authorRepository,
                                                       Converter<Author, AuthorDto> toAuthorDtoConverter) {
        return route()
                .GET("/api/v1/authors", request -> authorRepository.findAll()
                        .map(toAuthorDtoConverter::convert)
                        .collectList()
                        .flatMap(authors -> ok().bodyValue(authors))
                )
                .build();
    }
}
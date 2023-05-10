package ru.otus.spring.hw11.configuration.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Genre;
import ru.otus.spring.hw11.dto.GenreDto;
import ru.otus.spring.hw11.repository.GenreRepository;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class GenreRoutesConfiguration {

    @Bean
    public RouterFunction<ServerResponse> genreRoutes(GenreRepository genreRepository,
                                                      Converter<Genre, GenreDto> toGenreDtoConverter) {
        return route()
                .GET("/api/v1/genres", request -> genreRepository.findAll()
                        .map(toGenreDtoConverter::convert)
                        .collectList()
                        .flatMap(genres -> ok().bodyValue(genres))
                )
                .build();
    }
}
package ru.otus.spring.hw11.configuration.routes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Genre;
import ru.otus.spring.hw11.dto.GenreDto;
import ru.otus.spring.hw11.repository.GenreRepository;

import java.util.List;

import static java.util.Optional.of;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GenreRoutesConfigurationTest {

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private Converter<Genre, GenreDto> toGenreDtoConverter;

    @Autowired
    private RouterFunction<ServerResponse> genreRoutes;

    @Test
    @DisplayName("should return all genres")
    void shouldReturnAllGenres() {
        WebTestClient client = WebTestClient.bindToRouterFunction(genreRoutes).build();
        List<Genre> genres = List.of(new Genre("1", "Genre1"), new Genre("2", "Genre2"));
        List<GenreDto> genreDtos = List.of(new GenreDto("1", "Genre1"), new GenreDto("2", "Genre2"));

        when(genreRepository.findAll()).thenReturn(Flux.fromIterable(genres));
        when(toGenreDtoConverter.convert(genres.get(0))).thenReturn(of(genreDtos.get(0)));
        when(toGenreDtoConverter.convert(genres.get(1))).thenReturn(of(genreDtos.get(1)));

        client.get()
                .uri("/api/v1/genres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GenreDto.class)
                .isEqualTo(genreDtos);
    }
}
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
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.dto.AuthorDto;
import ru.otus.spring.hw11.repository.AuthorRepository;

import java.util.List;

import static java.util.Optional.of;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorRoutesConfigurationTest {

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private Converter<Author, AuthorDto> toAuthorDtoConverter;

    @Autowired
    private RouterFunction<ServerResponse> authorRoutes;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() {
        WebTestClient client = WebTestClient.bindToRouterFunction(authorRoutes).build();
        List<Author> authors = List.of(new Author("1", "Author1"), new Author("2", "Author2"));
        List<AuthorDto> authorDtos = List.of(new AuthorDto("1", "Author1"), new AuthorDto("2", "Author2"));

        when(authorRepository.findAll()).thenReturn(Flux.fromIterable(authors));
        when(toAuthorDtoConverter.convert(authors.get(0))).thenReturn(of(authorDtos.get(0)));
        when(toAuthorDtoConverter.convert(authors.get(1))).thenReturn(of(authorDtos.get(1)));

        client.get()
                .uri("/api/v1/authors")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(AuthorDto.class)
                .isEqualTo(authorDtos);
    }
}
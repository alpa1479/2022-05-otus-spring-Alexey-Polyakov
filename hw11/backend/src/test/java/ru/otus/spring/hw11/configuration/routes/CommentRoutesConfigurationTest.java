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
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.dto.SaveCommentDto;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.CommentRepository;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommentRoutesConfigurationTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private Converter<SaveCommentDto, Comment> toCommentConverter;

    @Autowired
    private RouterFunction<ServerResponse> commentRoutes;

    @Test
    @DisplayName("should add a comment to book")
    void shouldAddCommentToBook() {
        WebTestClient client = WebTestClient.bindToRouterFunction(commentRoutes).build();
        Book book = new Book();
        Comment comment = new Comment();
        SaveCommentDto saveCommentDto = new SaveCommentDto("bookId", "commentText");

        when(toCommentConverter.convert(saveCommentDto)).thenReturn(of(comment));
        when(commentRepository.save(comment)).thenReturn(Mono.just(comment));
        when(bookRepository.findByIdWithRelations(any())).thenReturn(Mono.just(book));
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        client.post()
                .uri("/api/v1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(saveCommentDto))
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("should delete all comments for book")
    void shouldDeleteAllCommentsForBook() {
        WebTestClient client = WebTestClient.bindToRouterFunction(commentRoutes).build();

        when(commentRepository.deleteWithRelationsByBookId(anyString())).thenReturn(Mono.empty());

        client.delete()
                .uri("/api/v1/comments/books/1")
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}
package ru.otus.spring.hw11.configuration.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.dto.SaveCommentDto;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.CommentRepository;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Configuration
public class CommentRoutesConfiguration {

    @Bean
    public RouterFunction<ServerResponse> commentRoutes(BookRepository bookRepository,
                                                        CommentRepository commentRepository,
                                                        Converter<SaveCommentDto, Comment> toCommentConverter) {
        return route()
                .POST("api/v1/comments", request -> request.bodyToMono(SaveCommentDto.class)
                        .map(toCommentConverter::convert)
                        .map(Optional::orElseThrow)
                        .flatMap(commentRepository::save)
                        .flatMap(newComment -> bookRepository.findByIdWithRelations(newComment.getBookId())
                                .doOnNext(book -> book.addCommentId(newComment.getId())))
                        .flatMap(bookRepository::save)
                        .then(status(HttpStatus.CREATED).build())
                )
                .DELETE("api/v1/comments/books/{id}", request -> commentRepository.deleteWithRelationsByBookId(request.pathVariable("id"))
                        .then(noContent().build())
                )
                .build();
    }
}
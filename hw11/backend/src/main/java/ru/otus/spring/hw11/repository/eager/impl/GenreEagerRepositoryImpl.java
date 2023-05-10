package ru.otus.spring.hw11.repository.eager.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.repository.CommentRepository;
import ru.otus.spring.hw11.repository.GenreRepository;
import ru.otus.spring.hw11.repository.eager.GenreEagerRepository;

@Service
public class GenreEagerRepositoryImpl implements GenreEagerRepository {

    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public GenreEagerRepositoryImpl(@Lazy GenreRepository genreRepository,
                                    CommentRepository commentRepository,
                                    ReactiveMongoTemplate mongoTemplate) {
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Void> deleteWithRelationsById(String id) {
        return genreRepository.deleteById(id).and(deleteBooks(id));
    }

    private Mono<Void> deleteBooks(String id) {
        Query findBooksByGenreQuery = new Query(Criteria.where("genreId").is(id));
        return mongoTemplate.findAllAndRemove(findBooksByGenreQuery, Book.class)
                .map(Book::getCommentIds)
                .flatMap(Flux::fromIterable)
                .collectList()
                .flatMap(commentRepository::deleteAllById);
    }
}

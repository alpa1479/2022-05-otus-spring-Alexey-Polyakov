package ru.otus.spring.hw11.repository.eager;

import reactor.core.publisher.Mono;

public interface CommentEagerRepository {

    Mono<Void> deleteWithRelationsById(String id);

    Mono<Void> deleteWithRelationsByBookId(String bookId);
}

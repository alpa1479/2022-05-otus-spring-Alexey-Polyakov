package ru.otus.spring.hw11.repository.eager;

import reactor.core.publisher.Mono;

public interface AuthorEagerRepository {

    Mono<Void> deleteWithRelationsById(String id);
}

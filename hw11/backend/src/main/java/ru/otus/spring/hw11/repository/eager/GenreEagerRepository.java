package ru.otus.spring.hw11.repository.eager;

import reactor.core.publisher.Mono;

public interface GenreEagerRepository {

    Mono<Void> deleteWithRelationsById(String id);
}

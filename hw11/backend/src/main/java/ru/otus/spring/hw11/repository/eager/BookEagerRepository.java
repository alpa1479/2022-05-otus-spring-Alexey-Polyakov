package ru.otus.spring.hw11.repository.eager;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.domain.Book;

public interface BookEagerRepository {

    Flux<Book> findAllWithRelations();

    Mono<Book> findByIdWithRelations(String id);

    Mono<Void> deleteWithRelationsById(String id);
}

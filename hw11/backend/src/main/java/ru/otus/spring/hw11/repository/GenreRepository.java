package ru.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.hw11.domain.Genre;
import ru.otus.spring.hw11.repository.eager.GenreEagerRepository;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String>, GenreEagerRepository {

    Flux<Genre> findByName(String name);
}

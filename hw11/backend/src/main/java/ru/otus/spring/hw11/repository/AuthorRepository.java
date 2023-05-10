package ru.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.repository.eager.AuthorEagerRepository;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String>, AuthorEagerRepository {

    Flux<Author> findByName(String name);
}

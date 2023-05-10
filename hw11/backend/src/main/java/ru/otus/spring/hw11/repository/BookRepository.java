package ru.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.repository.eager.BookEagerRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookEagerRepository {

    Flux<Book> findByTitle(String title);
}

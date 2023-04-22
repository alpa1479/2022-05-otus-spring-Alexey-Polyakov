package ru.otus.spring.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw08.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}

package ru.otus.spring.hw17.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw17.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}

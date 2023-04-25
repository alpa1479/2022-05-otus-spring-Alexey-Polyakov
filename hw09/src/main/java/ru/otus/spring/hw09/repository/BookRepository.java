package ru.otus.spring.hw09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw09.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}

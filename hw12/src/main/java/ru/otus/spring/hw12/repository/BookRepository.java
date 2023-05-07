package ru.otus.spring.hw12.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw12.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}

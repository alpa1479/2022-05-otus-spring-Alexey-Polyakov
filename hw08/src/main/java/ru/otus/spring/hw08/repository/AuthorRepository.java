package ru.otus.spring.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw08.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}

package ru.otus.spring.hw09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw09.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    @Override
    List<Author> findAllById(Iterable<String> ids);
}

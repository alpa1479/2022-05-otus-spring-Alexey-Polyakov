package ru.otus.spring.hw18.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw18.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    @Override
    List<Author> findAllById(Iterable<String> ids);

    Optional<Author> findByName(String name);
}

package ru.otus.spring.hw16.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw16.domain.Author;
import ru.otus.spring.hw16.repository.extension.AuthorRepositoryExtension;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryExtension {

    @Override
    void deleteById(String id);
}

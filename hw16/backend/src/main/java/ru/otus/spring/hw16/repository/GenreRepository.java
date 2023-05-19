package ru.otus.spring.hw16.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.hw16.domain.Genre;
import ru.otus.spring.hw16.repository.extension.GenreRepositoryExtension;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryExtension {

    @RestResource(exported = false)
    Optional<Genre> findByName(String name);

    @Override
    void deleteById(String id);
}

package ru.otus.spring.hw10.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw10.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findByName(String name);

    @Override
    List<Genre> findAllById(Iterable<String> ids);
}

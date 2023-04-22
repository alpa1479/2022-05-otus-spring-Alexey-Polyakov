package ru.otus.spring.hw08.services;

import ru.otus.spring.hw08.core.operations.CrudOperations;
import ru.otus.spring.hw08.domain.Genre;

import java.util.List;

public interface GenresService extends CrudOperations<Genre> {

    List<Genre> findAllById(Iterable<String> ids);
}

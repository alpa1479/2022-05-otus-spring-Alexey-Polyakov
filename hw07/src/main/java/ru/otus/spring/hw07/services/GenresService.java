package ru.otus.spring.hw07.services;

import ru.otus.spring.hw07.core.operations.CrudOperations;
import ru.otus.spring.hw07.domain.Genre;

import java.util.List;

public interface GenresService extends CrudOperations<Genre> {

    List<Genre> findAllById(Iterable<Long> ids);
}

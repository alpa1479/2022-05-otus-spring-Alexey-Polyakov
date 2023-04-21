package ru.otus.spring.hw06.repository;

import ru.otus.spring.hw06.core.operations.CrudOperations;
import ru.otus.spring.hw06.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends CrudOperations<Author> {

    List<Author> findByIds(Collection<Long> ids);

}

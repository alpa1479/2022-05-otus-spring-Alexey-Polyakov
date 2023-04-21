package ru.otus.spring.hw05.dao;

import ru.otus.spring.hw05.core.operations.CrudOperations;
import ru.otus.spring.hw05.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorDao extends CrudOperations<Author> {

    List<Author> findByIds(Collection<Long> ids);

}

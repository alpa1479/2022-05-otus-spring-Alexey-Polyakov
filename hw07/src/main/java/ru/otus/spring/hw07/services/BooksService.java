package ru.otus.spring.hw07.services;

import ru.otus.spring.hw07.core.operations.CrudOperations;
import ru.otus.spring.hw07.domain.Book;

public interface BooksService extends CrudOperations<Book> {

    void updateBookTitleById(Long id, String newTitle);
}

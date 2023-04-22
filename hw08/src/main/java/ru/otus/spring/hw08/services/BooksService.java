package ru.otus.spring.hw08.services;

import ru.otus.spring.hw08.core.operations.CrudOperations;
import ru.otus.spring.hw08.domain.Book;

public interface BooksService extends CrudOperations<Book> {

    void updateBookTitleById(String id, String newTitle);
}

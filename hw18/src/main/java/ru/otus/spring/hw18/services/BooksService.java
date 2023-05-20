package ru.otus.spring.hw18.services;

import ru.otus.spring.hw18.dto.BookDto;
import ru.otus.spring.hw18.dto.SaveBookDto;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<BookDto> findAll();

    Optional<SaveBookDto> findById(String id);

    void create(SaveBookDto bookToSave);

    void update(SaveBookDto bookToSave);

    void deleteById(String id);
}

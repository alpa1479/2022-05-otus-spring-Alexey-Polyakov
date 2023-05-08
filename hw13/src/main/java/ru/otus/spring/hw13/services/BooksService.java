package ru.otus.spring.hw13.services;

import ru.otus.spring.hw13.dto.BookDto;
import ru.otus.spring.hw13.dto.SaveBookDto;
import ru.otus.spring.hw13.dto.SaveCommentDto;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<BookDto> findAll();

    Optional<SaveBookDto> findById(String id);

    void create(SaveBookDto bookToSave);

    void addComment(SaveCommentDto saveCommentDto);

    void update(SaveBookDto bookToSave);

    void deleteById(String id);

    void deleteBookCommentsById(String id);
}

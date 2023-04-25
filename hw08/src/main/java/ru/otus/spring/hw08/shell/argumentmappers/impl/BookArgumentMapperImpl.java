package ru.otus.spring.hw08.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Author;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.domain.Genre;
import ru.otus.spring.hw08.shell.argumentmappers.BookArgumentMapper;

import java.util.List;

@Service
public class BookArgumentMapperImpl implements BookArgumentMapper {

    @Override
    public Book map(String title, String genreId, List<String> authorIds) {
        Book book = new Book(title, new Genre(genreId, null));
        List<Author> authors = authorIds.stream().map(id -> new Author(id, null)).toList();
        book.setAuthors(authors);
        return book;
    }

    @Override
    public Book map(String id, String title, String genreId, List<String> authorIds) {
        Book book = new Book(id, title, new Genre(genreId, null));
        List<Author> authors = authorIds.stream().map(authorId -> new Author(authorId, null)).toList();
        book.setAuthors(authors);
        return book;
    }
}

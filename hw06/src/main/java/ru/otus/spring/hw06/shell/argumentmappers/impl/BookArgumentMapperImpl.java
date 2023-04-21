package ru.otus.spring.hw06.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.domain.Author;
import ru.otus.spring.hw06.domain.Book;
import ru.otus.spring.hw06.domain.Genre;
import ru.otus.spring.hw06.shell.argumentmappers.BookArgumentMapper;

import java.util.List;

@Service
public class BookArgumentMapperImpl implements BookArgumentMapper {

    @Override
    public Book map(String title, long genreId, List<Long> authorIds) {
        Book book = new Book(title, new Genre(genreId));
        List<Author> authors = authorIds.stream().map(Author::new).toList();
        book.setAuthors(authors);
        return book;
    }

    @Override
    public Book map(long id, String title, long genreId, List<Long> authorIds) {
        Book book = new Book(id, title, new Genre(genreId));
        List<Author> authors = authorIds.stream().map(Author::new).toList();
        book.setAuthors(authors);
        return book;
    }
}

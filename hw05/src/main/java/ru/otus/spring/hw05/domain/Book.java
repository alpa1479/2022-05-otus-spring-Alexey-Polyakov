package ru.otus.spring.hw05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {

    private final long id;
    private final String title;
    private final Genre genre;
    private List<Author> authors = new ArrayList<>();

    public Book(String title, Genre genre) {
        this.id = 0;
        this.title = title;
        this.genre = genre;
    }

    public long getGenreId() {
        return genre.getId();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}

package ru.otus.spring.hw08.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String title;
    @DBRef
    private Genre genre;
    @DBRef
    private List<Author> authors = new ArrayList<>();
    @DBRef
    private List<Comment> comments = new ArrayList<>();

    public Book(String id, String title, Genre genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public Book(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String id) {
        this.id = id;
    }
}

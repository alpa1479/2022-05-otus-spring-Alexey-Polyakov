package ru.otus.spring.hw17.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Getter
@Setter
@Builder
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

    public String getGenreId() {
        return ofNullable(genre).map(Genre::getId).orElse(null);
    }

    public List<String> getAuthorIds() {
        return isEmpty(authors)
                ? emptyList()
                : authors.stream().map(Author::getId).toList();
    }

    public void addComment(Comment comment) {
        if (isEmpty(comments)) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }
}

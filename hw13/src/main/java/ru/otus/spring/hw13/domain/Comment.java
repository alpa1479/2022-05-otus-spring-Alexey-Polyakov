package ru.otus.spring.hw13.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String text;
    @DBRef
    private Book book;

    public Comment(String id, String bookId, String bookTitle, String text) {
        this.id = id;
        this.text = text;
        this.book = new Book(bookId, bookTitle);
    }

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public Comment(String text) {
        this.text = text;
    }
}

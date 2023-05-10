package ru.otus.spring.hw11.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    private String bookId;
    @Transient
    private Book book;

    public Comment(String text, String bookId) {
        this.text = text;
        this.bookId = bookId;
    }

    public Comment(String text) {
        this.text = text;
    }
}

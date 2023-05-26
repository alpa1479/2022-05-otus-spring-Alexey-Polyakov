package ru.otus.spring.hw14.domain.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookDocument {

    @Id
    private String id;
    private String title;
    @DBRef
    private GenreDocument genre;
    @DBRef
    private List<AuthorDocument> authors = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
}

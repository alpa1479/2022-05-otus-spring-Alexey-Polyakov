package ru.otus.spring.hw11.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
    private String genreId;
    private List<String> authorIds = new ArrayList<>();
    private List<String> commentIds = new ArrayList<>();
    @Transient
    private Genre genre;
    @Transient
    private List<Author> authors;
    @Transient
    private List<Comment> comments;

    public Book(String id, String title, String genreId, List<String> authorIds, List<String> commentIds) {
        this.id = id;
        this.title = title;
        this.genreId = genreId;
        this.authorIds = authorIds;
        this.commentIds = commentIds;
    }

    public Book(String title, String genreId) {
        this.title = title;
        this.genreId = genreId;
    }

    public void addAuthorId(String id) {
        if (isEmpty(authorIds)) {
            authorIds = new ArrayList<>();
        }
        authorIds.add(id);
    }

    public void addCommentId(String id) {
        if (isEmpty(commentIds)) {
            commentIds = new ArrayList<>();
        }
        commentIds.add(id);
    }
}

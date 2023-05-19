package ru.otus.spring.hw16.domain.excerpts;

import org.springframework.data.rest.core.config.Projection;
import ru.otus.spring.hw16.domain.Author;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.domain.Genre;

import java.util.List;

@Projection(types = {Book.class})
public interface BookExcerpt {

    String getId();

    String getTitle();

    Genre getGenre();

    List<Author> getAuthors();

    List<Comment> getComments();
}

package ru.otus.spring.hw08.shell.responsemappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.domain.Comment;
import ru.otus.spring.hw08.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;

@Service
public class CommentToStringResponseMapper implements ToStringResponseMapper<Comment> {

    @Override
    public String map(Comment comment) {
        if (comment == null) {
            return null;
        }
        String id = comment.getId();
        Book book = comment.getBook();
        String text = comment.getText();

        return book == null ? format("id: %s | text: %s", id, text)
                : format("id: %s | text: %s | book: {id: %s | title: %s}", id, text, book.getId(), book.getTitle());
    }
}

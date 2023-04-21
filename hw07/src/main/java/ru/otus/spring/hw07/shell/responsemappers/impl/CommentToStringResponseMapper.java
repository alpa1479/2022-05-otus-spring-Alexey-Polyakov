package ru.otus.spring.hw07.shell.responsemappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.domain.Comment;
import ru.otus.spring.hw07.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;

@Service
public class CommentToStringResponseMapper implements ToStringResponseMapper<Comment> {

    @Override
    public String map(Comment comment) {
        Book book = comment.getBook();
        return format("id: %s | text: %s | book: {id: %s | title: %s}",
                comment.getId(), comment.getText(), book.getId(), book.getTitle());
    }
}

package ru.otus.spring.hw08.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.domain.Comment;
import ru.otus.spring.hw08.shell.argumentmappers.CommentArgumentMapper;

@Service
public class CommentArgumentMapperImpl implements CommentArgumentMapper {

    @Override
    public Comment map(String bookId, String text) {
        return new Comment(null, text, new Book(bookId));
    }

    @Override
    public Comment map(String id, String bookId, String text) {
        return new Comment(id, text, new Book(bookId));
    }
}

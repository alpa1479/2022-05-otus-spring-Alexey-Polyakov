package ru.otus.spring.hw06.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.domain.Book;
import ru.otus.spring.hw06.domain.Comment;
import ru.otus.spring.hw06.shell.argumentmappers.CommentArgumentMapper;

@Service
public class CommentArgumentMapperImpl implements CommentArgumentMapper {

    @Override
    public Comment map(long bookId, String text) {
        return new Comment(text, new Book(bookId));
    }

    @Override
    public Comment map(long id, long bookId, String text) {
        return new Comment(id, text, new Book(bookId));
    }
}

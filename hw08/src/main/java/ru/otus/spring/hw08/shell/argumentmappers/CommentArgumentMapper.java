package ru.otus.spring.hw08.shell.argumentmappers;

import ru.otus.spring.hw08.domain.Comment;

public interface CommentArgumentMapper {

    Comment map(String bookId, String text);

    Comment map(String id, String bookId, String text);
}

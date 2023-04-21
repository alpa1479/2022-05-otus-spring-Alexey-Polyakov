package ru.otus.spring.hw07.shell.argumentmappers;

import ru.otus.spring.hw07.domain.Comment;

public interface CommentArgumentMapper {

    Comment map(long bookId, String text);

    Comment map(long id, long bookId, String text);
}

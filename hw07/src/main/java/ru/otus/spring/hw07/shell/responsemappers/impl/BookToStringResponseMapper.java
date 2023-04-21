package ru.otus.spring.hw07.shell.responsemappers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw07.domain.Author;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.domain.Genre;
import ru.otus.spring.hw07.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;

@Service
@RequiredArgsConstructor
public class BookToStringResponseMapper implements ToStringResponseMapper<Book> {

    private final ToStringResponseMapper<Genre> genreToStringResponseMapper;
    private final ToStringResponseMapper<Author> authorToStringResponseMapper;

    @Override
    public String map(Book book) {
        return format("id: %s | title: %s | genre: {%s} | authors: {%s}",
                book.getId(), book.getTitle(),
                genreToStringResponseMapper.map(book.getGenre()),
                authorToStringResponseMapper.map(book.getAuthors(), SPACE)
        );
    }
}

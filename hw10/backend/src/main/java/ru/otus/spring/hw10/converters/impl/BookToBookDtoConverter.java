package ru.otus.spring.hw10.converters.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw10.converters.Converter;
import ru.otus.spring.hw10.domain.Author;
import ru.otus.spring.hw10.domain.Book;
import ru.otus.spring.hw10.domain.Comment;
import ru.otus.spring.hw10.domain.Genre;
import ru.otus.spring.hw10.dto.AuthorDto;
import ru.otus.spring.hw10.dto.BookDto;
import ru.otus.spring.hw10.dto.CommentDto;
import ru.otus.spring.hw10.dto.GenreDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class BookToBookDtoConverter implements Converter<Book, BookDto> {

    private final Converter<Genre, GenreDto> toGenreDtoConverter;
    private final Converter<Author, AuthorDto> toAuthorDtoConverter;
    private final Converter<Comment, CommentDto> toCommentDtoConverter;

    @Override
    public Optional<BookDto> convert(Book book) {
        return ofNullable(book).map(this::map);
    }

    private BookDto map(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(toGenreDtoConverter.convert(book.getGenre()).orElse(null))
                .authors(toAuthorDtoConverter.convert(book.getAuthors()))
                .comments(toCommentDtoConverter.convert(book.getComments()))
                .build();
    }

}

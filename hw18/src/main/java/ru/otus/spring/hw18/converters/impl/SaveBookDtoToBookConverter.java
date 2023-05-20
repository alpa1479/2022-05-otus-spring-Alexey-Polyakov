package ru.otus.spring.hw18.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.converters.Converter;
import ru.otus.spring.hw18.domain.Author;
import ru.otus.spring.hw18.domain.Book;
import ru.otus.spring.hw18.domain.Genre;
import ru.otus.spring.hw18.dto.SaveBookDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class SaveBookDtoToBookConverter implements Converter<SaveBookDto, Book> {

    @Override
    public Optional<Book> convert(SaveBookDto saveBookDto) {
        return ofNullable(saveBookDto).map(this::map);
    }

    private Book map(SaveBookDto saveBookDto) {
        return Book.builder()
                .id(saveBookDto.getId())
                .title(saveBookDto.getTitle())
                .genre(Genre.withId(saveBookDto.getGenreId()))
                .authors(mapAuthors(saveBookDto.getAuthorIds()))
                .build();
    }

    private List<Author> mapAuthors(List<String> authorIds) {
        return authorIds.stream().map(Author::withId).collect(Collectors.toList());
    }
}

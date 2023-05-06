package ru.otus.spring.hw11.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.dto.SaveBookDto;

import java.util.Optional;

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
                .genreId(saveBookDto.getGenreId())
                .authorIds(saveBookDto.getAuthorIds())
                .commentIds(saveBookDto.getCommentIds())
                .build();
    }
}

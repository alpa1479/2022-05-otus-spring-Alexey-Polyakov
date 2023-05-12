package ru.otus.spring.hw17.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw17.converters.Converter;
import ru.otus.spring.hw17.domain.Genre;
import ru.otus.spring.hw17.dto.GenreDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class GenreToGenreDtoConverter implements Converter<Genre, GenreDto> {

    @Override
    public Optional<GenreDto> convert(Genre genre) {
        return ofNullable(genre).map(this::map);
    }

    private GenreDto map(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}

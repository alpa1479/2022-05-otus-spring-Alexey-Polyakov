package ru.otus.spring.hw13.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw13.converters.Converter;
import ru.otus.spring.hw13.domain.Genre;
import ru.otus.spring.hw13.dto.GenreDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class GenreDtoToGenreConverter implements Converter<GenreDto, Genre> {

    @Override
    public Optional<Genre> convert(GenreDto genre) {
        return ofNullable(genre).map(this::map);
    }

    private Genre map(GenreDto genre) {
        return new Genre(genre.getId(), genre.getName());
    }
}

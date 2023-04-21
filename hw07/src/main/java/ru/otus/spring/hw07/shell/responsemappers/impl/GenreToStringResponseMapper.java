package ru.otus.spring.hw07.shell.responsemappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw07.domain.Genre;
import ru.otus.spring.hw07.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;

@Service
public class GenreToStringResponseMapper implements ToStringResponseMapper<Genre> {

    @Override
    public String map(Genre genre) {
        return format("id: %s | name: %s", genre.getId(), genre.getName());
    }
}

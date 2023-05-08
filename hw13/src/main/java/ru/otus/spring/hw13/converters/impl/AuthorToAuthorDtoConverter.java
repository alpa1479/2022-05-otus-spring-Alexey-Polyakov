package ru.otus.spring.hw13.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw13.converters.Converter;
import ru.otus.spring.hw13.domain.Author;
import ru.otus.spring.hw13.dto.AuthorDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class AuthorToAuthorDtoConverter implements Converter<Author, AuthorDto> {

    @Override
    public Optional<AuthorDto> convert(Author author) {
        return ofNullable(author).map(this::map);
    }

    private AuthorDto map(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
}

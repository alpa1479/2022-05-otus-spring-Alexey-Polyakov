package ru.otus.spring.hw17.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw17.converters.Converter;
import ru.otus.spring.hw17.domain.Author;
import ru.otus.spring.hw17.dto.AuthorDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class AuthorDtoToAuthorConverter implements Converter<AuthorDto, Author> {

    @Override
    public Optional<Author> convert(AuthorDto author) {
        return ofNullable(author).map(this::map);
    }

    private Author map(AuthorDto author) {
        return new Author(author.getId(), author.getName());
    }
}

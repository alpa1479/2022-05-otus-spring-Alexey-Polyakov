package ru.otus.spring.hw18.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.converters.Converter;
import ru.otus.spring.hw18.domain.Author;
import ru.otus.spring.hw18.dto.AuthorDto;

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

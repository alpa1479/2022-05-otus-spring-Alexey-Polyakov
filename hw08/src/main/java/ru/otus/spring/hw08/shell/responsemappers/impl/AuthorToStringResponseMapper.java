package ru.otus.spring.hw08.shell.responsemappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Author;
import ru.otus.spring.hw08.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;

@Service
public class AuthorToStringResponseMapper implements ToStringResponseMapper<Author> {

    @Override
    public String map(Author author) {
        return author == null ? null : format("id: %s | name: %s", author.getId(), author.getName());
    }
}

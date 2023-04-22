package ru.otus.spring.hw08.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Author;
import ru.otus.spring.hw08.shell.argumentmappers.AuthorArgumentMapper;

@Service
public class AuthorArgumentMapperImpl implements AuthorArgumentMapper {

    @Override
    public Author map(String id, String name) {
        return new Author(id, name);
    }

    @Override
    public Author map(String name) {
        return new Author(null, name);
    }
}

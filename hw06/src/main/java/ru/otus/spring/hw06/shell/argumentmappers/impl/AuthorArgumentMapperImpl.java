package ru.otus.spring.hw06.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.domain.Author;
import ru.otus.spring.hw06.shell.argumentmappers.AuthorArgumentMapper;

@Service
public class AuthorArgumentMapperImpl implements AuthorArgumentMapper {

    @Override
    public Author map(long id, String name) {
        return new Author(id, name);
    }

    @Override
    public Author map(String name) {
        return new Author(name);
    }
}

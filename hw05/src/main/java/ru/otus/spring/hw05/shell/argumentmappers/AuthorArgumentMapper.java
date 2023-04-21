package ru.otus.spring.hw05.shell.argumentmappers;

import ru.otus.spring.hw05.domain.Author;

public interface AuthorArgumentMapper {

    Author map(long id, String name);

    Author map(String name);
}

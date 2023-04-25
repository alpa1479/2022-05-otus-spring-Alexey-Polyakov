package ru.otus.spring.hw08.shell.argumentmappers;

import ru.otus.spring.hw08.domain.Author;

public interface AuthorArgumentMapper {

    Author map(String id, String name);

    Author map(String name);
}

package ru.otus.spring.hw06.shell.argumentmappers;

import ru.otus.spring.hw06.domain.Author;

public interface AuthorArgumentMapper {

    Author map(long id, String name);

    Author map(String name);
}

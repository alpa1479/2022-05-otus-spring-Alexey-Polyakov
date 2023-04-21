package ru.otus.spring.hw07.shell.argumentmappers;

import ru.otus.spring.hw07.domain.Author;

public interface AuthorArgumentMapper {

    Author map(long id, String name);

    Author map(String name);
}

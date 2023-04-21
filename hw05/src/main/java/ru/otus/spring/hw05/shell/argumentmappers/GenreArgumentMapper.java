package ru.otus.spring.hw05.shell.argumentmappers;

import ru.otus.spring.hw05.domain.Genre;

public interface GenreArgumentMapper {

    Genre map(long id, String name);

    Genre map(String name);
}

package ru.otus.spring.hw08.shell.argumentmappers;

import ru.otus.spring.hw08.domain.Genre;

public interface GenreArgumentMapper {

    Genre map(String id, String name);

    Genre map(String name);
}

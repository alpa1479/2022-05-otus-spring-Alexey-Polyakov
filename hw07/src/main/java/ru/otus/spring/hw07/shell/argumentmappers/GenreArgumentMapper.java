package ru.otus.spring.hw07.shell.argumentmappers;

import ru.otus.spring.hw07.domain.Genre;

public interface GenreArgumentMapper {

    Genre map(long id, String name);

    Genre map(String name);
}

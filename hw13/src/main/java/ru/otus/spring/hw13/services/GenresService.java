package ru.otus.spring.hw13.services;

import ru.otus.spring.hw13.domain.Genre;
import ru.otus.spring.hw13.dto.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenresService {

    List<GenreDto> findAll();

    Optional<Genre> findById(String id);

    void save(GenreDto genre);

    Genre save(Genre genre);

    void deleteById(String id);
}

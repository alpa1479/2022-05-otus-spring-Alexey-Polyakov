package ru.otus.spring.hw09.services;

import ru.otus.spring.hw09.domain.Genre;
import ru.otus.spring.hw09.dto.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenresService {

    List<GenreDto> findAll();

    Optional<Genre> findByName(String name);

    Optional<GenreDto> findById(String id);

    void save(GenreDto genre);

    Genre save(Genre genre);

    void deleteById(String id);
}

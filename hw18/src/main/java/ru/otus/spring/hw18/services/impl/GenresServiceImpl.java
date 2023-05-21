package ru.otus.spring.hw18.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.converters.Converter;
import ru.otus.spring.hw18.domain.Genre;
import ru.otus.spring.hw18.dto.GenreDto;
import ru.otus.spring.hw18.repository.GenreRepository;
import ru.otus.spring.hw18.repository.cascade.GenreRepositoryCascade;
import ru.otus.spring.hw18.services.GenresService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {

    private final GenreRepository genreRepository;
    private final GenreRepositoryCascade genreRepositoryCascade;

    private final Converter<Genre, GenreDto> toGenreDtoConverter;
    private final Converter<GenreDto, Genre> toGenreConverter;

    @Override
    @CircuitBreaker(name = "mongodb")
    public List<GenreDto> findAll() {
        return toGenreDtoConverter.convert(genreRepository.findAll());
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void save(GenreDto genre) {
        toGenreConverter.convert(genre).ifPresent(genreRepository::save);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void deleteById(String id) {
        genreRepositoryCascade.cascadeDeleteById(id);
    }
}

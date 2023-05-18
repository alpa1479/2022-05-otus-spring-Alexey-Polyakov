package ru.otus.spring.hw17.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw17.converters.Converter;
import ru.otus.spring.hw17.domain.Genre;
import ru.otus.spring.hw17.dto.GenreDto;
import ru.otus.spring.hw17.repository.GenreRepository;
import ru.otus.spring.hw17.repository.cascade.GenreRepositoryCascade;
import ru.otus.spring.hw17.services.GenresService;

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
    public List<GenreDto> findAll() {
        return toGenreDtoConverter.convert(genreRepository.findAll());
    }

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public void save(GenreDto genre) {
        toGenreConverter.convert(genre).ifPresent(genreRepository::save);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteById(String id) {
        genreRepositoryCascade.cascadeDeleteById(id);
    }
}

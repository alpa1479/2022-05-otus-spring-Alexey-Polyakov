package ru.otus.spring.hw08.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Genre;
import ru.otus.spring.hw08.repository.GenreRepository;
import ru.otus.spring.hw08.repository.cascade.GenreRepositoryCascade;
import ru.otus.spring.hw08.services.GenresService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {

    private final GenreRepository genreRepository;
    private final GenreRepositoryCascade genreRepositoryCascade;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findAllById(Iterable<String> ids) {
        return genreRepository.findAllById(ids);
    }

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
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

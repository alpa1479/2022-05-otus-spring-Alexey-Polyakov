package ru.otus.spring.hw18.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.converters.Converter;
import ru.otus.spring.hw18.domain.Author;
import ru.otus.spring.hw18.dto.AuthorDto;
import ru.otus.spring.hw18.repository.AuthorRepository;
import ru.otus.spring.hw18.repository.cascade.AuthorRepositoryCascade;
import ru.otus.spring.hw18.services.AuthorsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;
    private final AuthorRepositoryCascade authorRepositoryCascade;

    private final Converter<Author, AuthorDto> toAuthorDtoConverter;
    private final Converter<AuthorDto, Author> toAuthorConverter;

    @Override
    @CircuitBreaker(name = "mongodb")
    public List<AuthorDto> findAll() {
        return toAuthorDtoConverter.convert(authorRepository.findAll());
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public List<Author> findAllById(List<String> ids) {
        return authorRepository.findAllById(ids);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Optional<AuthorDto> findById(String id) {
        return authorRepository.findById(id).flatMap(toAuthorDtoConverter::convert);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Optional<AuthorDto> findByName(String name) {
        return authorRepository.findByName(name).flatMap(toAuthorDtoConverter::convert);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void save(AuthorDto author) {
        toAuthorConverter.convert(author).ifPresent(authorRepository::save);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void deleteById(String id) {
        authorRepositoryCascade.cascadeDeleteById(id);
    }
}

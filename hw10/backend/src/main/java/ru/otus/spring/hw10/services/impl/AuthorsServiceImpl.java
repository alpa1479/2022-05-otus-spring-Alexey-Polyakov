package ru.otus.spring.hw10.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw10.converters.Converter;
import ru.otus.spring.hw10.domain.Author;
import ru.otus.spring.hw10.dto.AuthorDto;
import ru.otus.spring.hw10.repository.AuthorRepository;
import ru.otus.spring.hw10.repository.cascade.AuthorRepositoryCascade;
import ru.otus.spring.hw10.services.AuthorsService;

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
    public List<AuthorDto> findAll() {
        return toAuthorDtoConverter.convert(authorRepository.findAll());
    }

    @Override
    public List<Author> findAllById(List<String> ids) {
        return authorRepository.findAllById(ids);
    }

    @Override
    public Optional<AuthorDto> findById(String id) {
        return authorRepository.findById(id).flatMap(toAuthorDtoConverter::convert);
    }

    @Override
    public void save(AuthorDto author) {
        toAuthorConverter.convert(author).ifPresent(authorRepository::save);
    }

    @Override
    public void deleteById(String id) {
        authorRepositoryCascade.cascadeDeleteById(id);
    }
}
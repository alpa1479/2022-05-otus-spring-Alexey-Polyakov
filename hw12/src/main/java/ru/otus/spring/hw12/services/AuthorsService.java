package ru.otus.spring.hw12.services;

import ru.otus.spring.hw12.domain.Author;
import ru.otus.spring.hw12.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<AuthorDto> findAll();

    List<Author> findAllById(List<String> ids);

    Optional<AuthorDto> findById(String id);

    void save(AuthorDto author);

    void deleteById(String id);
}

package ru.otus.spring.hw18.services;

import ru.otus.spring.hw18.domain.Author;
import ru.otus.spring.hw18.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<AuthorDto> findAll();

    List<Author> findAllById(List<String> ids);

    Optional<AuthorDto> findById(String id);

    Optional<AuthorDto> findByName(String name);

    void save(AuthorDto author);

    void deleteById(String id);
}

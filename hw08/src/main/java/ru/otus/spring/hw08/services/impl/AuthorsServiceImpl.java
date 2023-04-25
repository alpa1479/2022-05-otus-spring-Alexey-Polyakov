package ru.otus.spring.hw08.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Author;
import ru.otus.spring.hw08.repository.AuthorRepository;
import ru.otus.spring.hw08.repository.cascade.AuthorRepositoryCascade;
import ru.otus.spring.hw08.services.AuthorsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;
    private final AuthorRepositoryCascade authorRepositoryCascade;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(String id) {
        authorRepositoryCascade.cascadeDeleteById(id);
    }
}

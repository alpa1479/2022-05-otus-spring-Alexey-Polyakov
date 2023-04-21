package ru.otus.spring.hw06.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw06.domain.Author;
import ru.otus.spring.hw06.repository.AuthorRepository;
import ru.otus.spring.hw06.services.AuthorsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public Author create(Author author) {
        return authorRepository.create(author);
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return authorRepository.update(author);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }
}

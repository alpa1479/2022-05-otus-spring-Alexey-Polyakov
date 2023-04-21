package ru.otus.spring.hw05.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw05.dao.AuthorDao;
import ru.otus.spring.hw05.domain.Author;
import ru.otus.spring.hw05.services.AuthorsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorDao authorDao;
    private final TransactionManager transactionManager;

    @Override
    public List<Author> findAll() {
        return transactionManager.doInReadOnlyTransaction(authorDao::findAll);
    }

    @Override
    public Author findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> authorDao.findById(id));
    }

    @Override
    public long create(Author author) {
        return transactionManager.doInTransaction(() -> authorDao.create(author));
    }

    @Override
    public void update(Author author) {
        transactionManager.doInTransaction(() -> authorDao.update(author));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> authorDao.deleteById(id));
    }
}

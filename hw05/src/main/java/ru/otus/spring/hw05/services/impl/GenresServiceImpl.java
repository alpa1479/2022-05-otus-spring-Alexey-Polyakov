package ru.otus.spring.hw05.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw05.dao.GenreDao;
import ru.otus.spring.hw05.domain.Genre;
import ru.otus.spring.hw05.services.GenresService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {

    private final GenreDao genreDao;
    private final TransactionManager transactionManager;

    @Override
    public List<Genre> findAll() {
        return transactionManager.doInReadOnlyTransaction(genreDao::findAll);
    }

    @Override
    public Genre findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> genreDao.findById(id));
    }

    @Override
    public long create(Genre genre) {
        return transactionManager.doInTransaction(() -> genreDao.create(genre));
    }

    @Override
    public void update(Genre genre) {
        transactionManager.doInTransaction(() -> genreDao.update(genre));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> genreDao.deleteById(id));
    }
}

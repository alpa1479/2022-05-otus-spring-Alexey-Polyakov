package ru.otus.spring.hw05.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw05.dao.BookDao;
import ru.otus.spring.hw05.domain.Book;
import ru.otus.spring.hw05.services.BooksService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookDao bookDao;
    private final TransactionManager transactionManager;

    @Override
    public List<Book> findAll() {
        return transactionManager.doInReadOnlyTransaction(bookDao::findAll);
    }

    @Override
    public Book findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> bookDao.findById(id));
    }

    @Override
    public long create(Book book) {
        return transactionManager.doInTransaction(() -> bookDao.create(book));
    }

    @Override
    public void update(Book book) {
        transactionManager.doInTransaction(() -> bookDao.update(book));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> bookDao.deleteById(id));
    }
}

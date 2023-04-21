package ru.otus.spring.hw07.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.repository.BookRepository;
import ru.otus.spring.hw07.services.BooksService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        if (CollectionUtils.isNotEmpty(books)) {
            books.stream().map(Book::getAuthors).forEach(Hibernate::initialize);
        }
        return books;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(b -> Hibernate.initialize(b.getAuthors()));
        return book;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void updateBookTitleById(Long id, String newTitle) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(b -> b.setTitle(newTitle));
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}

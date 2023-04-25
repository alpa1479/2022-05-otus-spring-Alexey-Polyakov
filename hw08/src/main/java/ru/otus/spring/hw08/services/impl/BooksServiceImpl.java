package ru.otus.spring.hw08.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.repository.BookRepository;
import ru.otus.spring.hw08.repository.cascade.BookRepositoryCascade;
import ru.otus.spring.hw08.services.BooksService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final BookRepositoryCascade bookRepositoryCascade;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void updateBookTitleById(String id, String newTitle) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setTitle(newTitle);
            bookRepository.save(book);
        });
    }

    @Override
    public void deleteById(String id) {
        bookRepositoryCascade.cascadeDeleteById(id);
    }
}

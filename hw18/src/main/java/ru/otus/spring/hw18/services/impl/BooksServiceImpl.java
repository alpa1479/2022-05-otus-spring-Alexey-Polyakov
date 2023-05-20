package ru.otus.spring.hw18.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.converters.Converter;
import ru.otus.spring.hw18.domain.Author;
import ru.otus.spring.hw18.domain.Book;
import ru.otus.spring.hw18.domain.Comment;
import ru.otus.spring.hw18.domain.Genre;
import ru.otus.spring.hw18.dto.BookDto;
import ru.otus.spring.hw18.dto.SaveBookDto;
import ru.otus.spring.hw18.repository.BookRepository;
import ru.otus.spring.hw18.repository.cascade.BookRepositoryCascade;
import ru.otus.spring.hw18.services.AuthorsService;
import ru.otus.spring.hw18.services.BooksService;
import ru.otus.spring.hw18.services.CommentsService;
import ru.otus.spring.hw18.services.GenresService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final BookRepositoryCascade bookRepositoryCascade;

    private final GenresService genresService;
    private final AuthorsService authorsService;
    private final CommentsService commentsService;

    private final Converter<Book, BookDto> toBookDtoConverter;
    private final Converter<Book, SaveBookDto> toSaveBookDtoConverter;
    private final Converter<SaveBookDto, Book> toBookConverter;

    @Override
    @CircuitBreaker(name = "mongodb")
    public List<BookDto> findAll() {
        return toBookDtoConverter.convert(bookRepository.findAll());
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public Optional<SaveBookDto> findById(String id) {
        return bookRepository.findById(id).flatMap(toSaveBookDtoConverter::convert);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void create(SaveBookDto bookToSave) {
        toBookConverter.convert(bookToSave).ifPresent(this::create);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void update(SaveBookDto bookToSave) {
        toBookConverter.convert(bookToSave).ifPresent(this::update);
    }

    @Override
    @CircuitBreaker(name = "mongodb")
    public void deleteById(String id) {
        bookRepositoryCascade.cascadeDeleteById(id);
    }

    private void create(Book book) {
        setGenre(book);
        setAuthors(book);
        bookRepository.save(book);
    }

    private void update(Book book) {
        setGenre(book);
        setAuthors(book);
        setComments(book);
        bookRepository.save(book);
    }

    private void setGenre(Book book) {
        Genre genre = genresService.findById(book.getGenreId()).orElse(null);
        book.setGenre(genre);
    }

    private void setAuthors(Book book) {
        List<Author> authors = authorsService.findAllById(book.getAuthorIds());
        book.setAuthors(authors);
    }

    private void setComments(Book book) {
        List<Comment> comments = commentsService.findByBookId(book.getId());
        book.setComments(comments);
    }
}

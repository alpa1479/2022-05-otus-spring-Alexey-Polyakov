package ru.otus.spring.hw09.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw09.converters.Converter;
import ru.otus.spring.hw09.domain.Author;
import ru.otus.spring.hw09.domain.Book;
import ru.otus.spring.hw09.domain.Comment;
import ru.otus.spring.hw09.domain.Genre;
import ru.otus.spring.hw09.dto.BookDto;
import ru.otus.spring.hw09.dto.SaveBookDto;
import ru.otus.spring.hw09.dto.SaveCommentDto;
import ru.otus.spring.hw09.repository.BookRepository;
import ru.otus.spring.hw09.repository.cascade.BookRepositoryCascade;
import ru.otus.spring.hw09.services.AuthorsService;
import ru.otus.spring.hw09.services.BooksService;
import ru.otus.spring.hw09.services.CommentsService;
import ru.otus.spring.hw09.services.GenresService;

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
    public List<BookDto> findAll() {
        return toBookDtoConverter.convert(bookRepository.findAll());
    }

    @Override
    public Optional<SaveBookDto> findById(String id) {
        return bookRepository.findById(id).flatMap(toSaveBookDtoConverter::convert);
    }

    @Override
    public void create(SaveBookDto bookToSave) {
        toBookConverter.convert(bookToSave).ifPresent(this::create);
    }

    @Override
    public void addComment(SaveCommentDto saveCommentDto) {
        bookRepository.findById(saveCommentDto.getBookId())
                .ifPresent(book -> {
                    String text = saveCommentDto.getText();
                    Comment comment = commentsService.save(new Comment(text, book));
                    book.addComment(comment);
                    bookRepository.save(book);
                });
    }

    @Override
    public void update(SaveBookDto bookToSave) {
        toBookConverter.convert(bookToSave).ifPresent(this::update);
    }

    @Override
    public void deleteById(String id) {
        bookRepositoryCascade.cascadeDeleteById(id);
    }

    @Override
    public void deleteBookCommentsById(String id) {
        commentsService.deleteAllByBookId(id);
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
        String genreId = book.getGenreId();
        Genre genre = genresService.findById(genreId).orElse(null);
        book.setGenre(genre);
    }

    private void setAuthors(Book book) {
        List<Author> authors = authorsService.findAllById(book.getAuthorIds());
        book.setAuthors(authors);
    }

    private void setComments(Book book) {
        List<Comment> comments = commentsService.findCommentsByBookId(book.getId());
        book.setComments(comments);
    }
}

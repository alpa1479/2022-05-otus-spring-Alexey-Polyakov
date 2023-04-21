package ru.otus.spring.hw05.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.core.exception.EmptyKeyException;
import ru.otus.spring.hw05.dao.AuthorDao;
import ru.otus.spring.hw05.dao.BookDao;
import ru.otus.spring.hw05.dao.impl.relations.BookAuthorRelation;
import ru.otus.spring.hw05.dao.impl.rowmappers.BookAuthorRelationMapper;
import ru.otus.spring.hw05.dao.impl.rowmappers.BookMapper;
import ru.otus.spring.hw05.domain.Author;
import ru.otus.spring.hw05.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;

import static java.util.Collections.emptyList;
import static java.util.Map.of;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static ru.otus.spring.hw05.core.util.Validations.requireNonNull;
import static ru.otus.spring.hw05.core.util.Validations.requireValue;

@Service
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final AuthorDao authorDao;
    private final NamedParameterJdbcOperations operations;

    @Override
    public List<Book> findAll() {
        List<Book> books = operations.query("select books.id as id, title, genre_id, genres.name as genre_name from books " +
                "join genres on genres.id = books.genre_id", new BookMapper());

        List<BookAuthorRelation> relations = findBookAuthorRelations();
        List<Long> authorIds = extractAuthorIdsFromRelations(relations);
        List<Author> authors = findAuthorsByIds(authorIds);

        addAuthorsToBooks(books, relations, authors);
        return books;
    }

    @Override
    public Book findById(long id) {
        Book book = operations.queryForObject("select books.id as id, title, genre_id, genres.name as genre_name from books " +
                "join genres on genres.id = books.genre_id and books.id = :id", of("id", id), new BookMapper());

        List<BookAuthorRelation> relations = findBookAuthorRelationsByBookId(book.getId());
        List<Long> authorIds = extractAuthorIdsFromRelations(relations);
        List<Author> authors = findAuthorsByIds(authorIds);

        book.setAuthors(authors);
        return book;
    }

    @Override
    public long create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        operations.update("insert into books (title, genre_id) values (:title, :genre_id)",
                new MapSqlParameterSource(of("title", book.getTitle(), "genre_id", book.getGenreId())), keyHolder, new String[]{"id"});

        long bookId = requireNonNull(keyHolder.getKey(), EmptyKeyException::new).longValue();
        insertBookAuthorsRelations(bookId, book.getAuthors());
        return bookId;
    }

    @Override
    public void update(Book book) {
        long bookId = book.getId();
        String sql = "update books set title = :title, genre_id = :genre_id where id = :id";
        int rowsAffected = operations.update(sql, of("id", bookId, "title", book.getTitle(), "genre_id", book.getGenreId()));
        int expectedRowsAffected = 1;
        requireValue(rowsAffected, expectedRowsAffected, () -> new JdbcUpdateAffectedIncorrectNumberOfRowsException(sql, expectedRowsAffected, rowsAffected));

        removeBookAuthorsRelations(bookId);
        insertBookAuthorsRelations(bookId, book.getAuthors());
    }

    @Override
    public void deleteById(long id) {
        operations.update("delete from books where id = :id", of("id", id));
    }

    private List<BookAuthorRelation> findBookAuthorRelations() {
        return operations.query("select book_id, author_id from books_authors", new BookAuthorRelationMapper());
    }

    private List<BookAuthorRelation> findBookAuthorRelationsByBookId(long id) {
        return operations.query("select book_id, author_id from books_authors where book_id = :book_id",
                of("book_id", id), new BookAuthorRelationMapper());
    }

    private List<Long> extractAuthorIdsFromRelations(List<BookAuthorRelation> relations) {
        return isNotEmpty(relations) ? relations.stream().map(BookAuthorRelation::getAuthorId).toList() : emptyList();
    }

    private List<Author> findAuthorsByIds(List<Long> authorIds) {
        return isNotEmpty(authorIds) ? authorDao.findByIds(authorIds) : emptyList();
    }

    private void addAuthorsToBooks(List<Book> books, List<BookAuthorRelation> relations, List<Author> authors) {
        if (isEmpty(books) || isEmpty(relations) || isEmpty(authors)) {
            return;
        }

        Map<Long, Book> booksMap = books.stream().collect(toMap(Book::getId, Function.identity()));
        Map<Long, Author> authorsMap = authors.stream().collect(toMap(Author::getId, Function.identity()));

        for (BookAuthorRelation relation : relations) {
            Book book = booksMap.get(relation.getBookId());
            Author author = authorsMap.get(relation.getAuthorId());
            book.addAuthor(author);
        }
    }

    private void insertBookAuthorsRelations(long bookId, List<Author> authors) {
        Map<String, Long>[] batchValues = authors.stream()
                .map(Author::getId)
                .map(authorId -> of("book_id", bookId, "author_id", authorId))
                .toArray((IntFunction<Map<String, Long>[]>) Map[]::new);
        operations.batchUpdate("insert into books_authors (book_id, author_id) values (:book_id, :author_id)", batchValues);
    }

    private void removeBookAuthorsRelations(long bookId) {
        operations.update("delete from books_authors where book_id = :book_id", of("book_id", bookId));
    }
}

package ru.otus.spring.hw17.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.hw17.domain.Author;
import ru.otus.spring.hw17.domain.Book;
import ru.otus.spring.hw17.domain.Comment;
import ru.otus.spring.hw17.domain.Genre;
import ru.otus.spring.hw17.repository.AuthorRepository;
import ru.otus.spring.hw17.repository.BookRepository;
import ru.otus.spring.hw17.repository.CommentRepository;
import ru.otus.spring.hw17.repository.GenreRepository;

import java.util.List;

import static java.util.List.of;

@ChangeLog
public class TestDatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDatabase", author = "alpa", runAlways = true)
    public void dropDatabase(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "saveAuthors", author = "alpa")
    public void saveAuthors(AuthorRepository authorRepository) {
        authorRepository.saveAll(of(
                new Author("Thomas Artis"),
                new Author("Rania Martinez")
        ));
    }

    @ChangeSet(order = "003", id = "saveGenres", author = "alpa")
    public void saveGenres(GenreRepository genreRepository) {
        genreRepository.saveAll(of(
                new Genre("drama"),
                new Genre("horror"),
                new Genre("thriller")
        ));
    }

    // @formatter:off
    @ChangeSet(order = "004", id = "saveBooks", author = "alpa")
    public void saveBooks(BookRepository bookRepository, GenreRepository genreRepository) {
        bookRepository.saveAll(of(
                new Book("The Oblong Box",                  findGenreByName("drama", genreRepository)),
                new Book("Skin Deep",                       findGenreByName("horror", genreRepository)),
                new Book("Summer Catch",                    findGenreByName("thriller", genreRepository))
        ));
    }
    // @formatter:on

    @ChangeSet(order = "005", id = "saveComments", author = "alpa")
    public void saveComments(CommentRepository commentRepository) {
        commentRepository.saveAll(of(
                new Comment("Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."),
                new Comment("Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst."),
                new Comment("Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.")
        ));
    }

    @ChangeSet(order = "006", id = "linkBooksWithAuthors", author = "alpa")
    public void linkBooksWithAuthors(BookRepository bookRepository, AuthorRepository authorRepository) {
        List<Author> authors = authorRepository.findAll();
        List<Book> books = bookRepository.findAll();
        linkAuthorsSequentially(books, authors);
        bookRepository.saveAll(books);
    }

    @ChangeSet(order = "007", id = "linkCommentsWithBook", author = "alpa")
    public void linkCommentsWithBook(BookRepository bookRepository, CommentRepository commentRepository) {
        List<Comment> comments = commentRepository.findAll();
        List<Book> books = bookRepository.findAll();
        linkBooksSequentially(comments, books);
        commentRepository.saveAll(comments);
    }

    @ChangeSet(order = "008", id = "linkBooksWithComments", author = "alpa")
    public void linkBooksWithComments(BookRepository bookRepository, CommentRepository commentRepository) {
        List<Comment> comments = commentRepository.findAll();
        List<Book> books = bookRepository.findAll();
        linkCommentsSequentially(books, comments);
        bookRepository.saveAll(books);
    }

    private Genre findGenreByName(String name, GenreRepository genreRepository) {
        return genreRepository.findByName(name).orElseThrow();
    }

    private void linkAuthorsSequentially(List<Book> books, List<Author> authors) {
        for (int bookIndex = 0, authorIndex = 0; bookIndex < books.size(); bookIndex++, authorIndex++) {
            if (authorIndex >= authors.size()) {
                authorIndex = 0;
            }
            Book book = books.get(bookIndex);
            Author author = authors.get(authorIndex);
            List<Author> bookAuthors = book.getAuthors();
            bookAuthors.add(author);
        }
    }

    private void linkCommentsSequentially(List<Book> books, List<Comment> comments) {
        for (int bookIndex = 0, commentIndex = 0; commentIndex < comments.size(); bookIndex++, commentIndex++) {
            if (bookIndex >= books.size()) {
                bookIndex = 0;
            }
            Book book = books.get(bookIndex);
            Comment comment = comments.get(commentIndex);
            List<Comment> bookComments = book.getComments();
            bookComments.add(comment);
        }
    }

    private void linkBooksSequentially(List<Comment> comments, List<Book> books) {
        for (int commentIndex = 0, bookToLinkIndex = 0; commentIndex < comments.size(); commentIndex++, bookToLinkIndex++) {
            Comment comment = comments.get(commentIndex);
            if (bookToLinkIndex >= books.size()) {
                bookToLinkIndex = 0;
            }
            Book bookToLink = books.get(bookToLinkIndex);
            comment.setBook(bookToLink);
        }
    }
}

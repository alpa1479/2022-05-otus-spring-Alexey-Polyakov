package ru.otus.spring.hw11.mongock.changeunits;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.repository.AuthorRepository;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@ChangeUnit(id = "BookRelationsChangeUnit", order = "7")
public class BookRelationsChangeUnit {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Execution
    public void linkBooksWithAuthorsAndComments() {
        List<Author> authors = authorRepository.findAll().collectList().block();
        List<Comment> comments = commentRepository.findAll().collectList().block();
        List<Book> books = bookRepository.findAll().collectList().block();
        linkAuthorsSequentially(books, authors);
        linkCommentsSequentially(books, comments);
        bookRepository.saveAll(books).blockLast();
    }

    @RollbackExecution
    public void rollback() {
    }

    private void linkAuthorsSequentially(List<Book> books, List<Author> authors) {
        for (int bookIndex = 0, authorIndex = 0; bookIndex < books.size(); bookIndex++, authorIndex++) {
            if (authorIndex >= authors.size()) {
                authorIndex = 0;
            }
            Book book = books.get(bookIndex);
            Author author = authors.get(authorIndex);
            book.addAuthorId(author.getId());
        }
    }

    private void linkCommentsSequentially(List<Book> books, List<Comment> comments) {
        for (int bookIndex = 0, commentIndex = 0; commentIndex < comments.size(); bookIndex++, commentIndex++) {
            if (bookIndex >= books.size()) {
                bookIndex = 0;
            }
            Book book = books.get(bookIndex);
            Comment comment = comments.get(commentIndex);
            book.addCommentId(comment.getId());
        }
    }
}

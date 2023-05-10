package ru.otus.spring.hw11.mongock.changeunits;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@ChangeUnit(id = "CommentRelationsChangeUnit", order = "6")
public class CommentRelationsChangeUnit {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Execution
    public void linkCommentsWithBook() {
        List<Comment> comments = commentRepository.findAll().collectList().block();
        List<Book> books = bookRepository.findAll().collectList().block();
        linkBooksSequentially(comments, books);
        commentRepository.saveAll(comments).blockLast();
    }

    @RollbackExecution
    public void rollback() {
    }

    private void linkBooksSequentially(List<Comment> comments, List<Book> books) {
        for (int commentIndex = 0, bookToLinkIndex = 0; commentIndex < comments.size(); commentIndex++, bookToLinkIndex++) {
            Comment comment = comments.get(commentIndex);
            if (bookToLinkIndex >= books.size()) {
                bookToLinkIndex = 0;
            }
            Book bookToLink = books.get(bookToLinkIndex);
            comment.setBookId(bookToLink.getId());
        }
    }
}

package ru.otus.spring.hw12.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw12.domain.Book;
import ru.otus.spring.hw12.domain.Comment;
import ru.otus.spring.hw12.repository.BookRepository;
import ru.otus.spring.hw12.repository.CommentRepository;
import ru.otus.spring.hw12.repository.cascade.CommentRepositoryCascade;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given comment repository")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepositoryCascade commentRepositoryCascade;

    @Test
    @DisplayName("should return all comments")
    void shouldReturnAllComments() {
        assertThat(commentRepository.findAll())
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("book")
                .isEqualTo(getExpectedComments());
    }

    @Test
    @DisplayName("should return comment by id")
    void shouldReturnCommentById() {
        Comment expectedComment = commentRepository.findAll().stream().findFirst().orElseThrow();
        assertThat(commentRepository.findById(expectedComment.getId()))
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("should create comment")
    void shouldCreateComment() {
        Comment newComment = new Comment("text");
        Comment createdComment = commentRepository.save(newComment);

        assertThat(createdComment).matches(comment -> comment.getText().equals(newComment.getText()));
        commentRepository.deleteById(createdComment.getId());
    }

    @Test
    @DisplayName("should update comment")
    void shouldUpdateComment() {
        Comment newComment = new Comment("text");
        Comment createdComment = commentRepository.save(newComment);

        createdComment.setText("changed text");
        Comment updatedComment = commentRepository.save(createdComment);

        assertThat(updatedComment).matches(comment -> comment.getText().equals("changed text"));
        commentRepository.deleteById(updatedComment.getId());
    }

    @Test
    @DisplayName("should delete comment by id")
    void shouldDeleteCommentById() {
        Comment newComment = new Comment("text");
        Comment createdComment = commentRepository.save(newComment);

        Book newBook = new Book(null, "new book title", null, emptyList(), List.of(createdComment));
        Book createdBook = bookRepository.save(newBook);

        String commentId = createdComment.getId();
        commentRepositoryCascade.cascadeDeleteById(commentId);

        assertThat(commentRepository.findById(commentId)).isNotPresent();
        assertThat(bookRepository.findAll()
                .stream()
                .filter(book -> book.getComments().stream().anyMatch(c -> c.getId().equals(commentId)))
                .toList()).isEmpty();
        bookRepository.deleteById(createdBook.getId());
    }


    List<Comment> getExpectedComments() {
        return List.of(
                new Comment("Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."),
                new Comment("Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst."),
                new Comment("Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.")
        );
    }
}
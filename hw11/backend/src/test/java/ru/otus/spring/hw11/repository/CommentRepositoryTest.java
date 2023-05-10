package ru.otus.spring.hw11.repository;

import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;

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
    private MongockInitializingBeanRunner mongockInitializingBeanRunner;

    @Test
    @DisplayName("should return all comments")
    void shouldReturnAllComments() {
        StepVerifier.create(commentRepository.findAll().collectList())
                .assertNext(comments -> assertThat(comments)
                        .usingRecursiveComparison()
                        .ignoringCollectionOrder()
                        .ignoringExpectedNullFields()
                        .isEqualTo(getExpectedComments())
                )
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should return comment by id")
    void shouldReturnCommentById() {
        Comment expectedComment = commentRepository.findAll().blockFirst();

        StepVerifier.create(commentRepository.findById(expectedComment.getId()))
                .assertNext(comment ->
                        assertThat(comment)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedComment))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should create comment")
    void shouldCreateComment() {
        Comment expectedComment = commentRepository.save(new Comment("New Comment")).block();

        String commentId = expectedComment.getId();
        StepVerifier.create(commentRepository.findById(commentId))
                .assertNext(comment ->
                        assertThat(comment)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedComment))
                .expectComplete()
                .verify();

        commentRepository.deleteById(commentId).block();
    }

    @Test
    @DisplayName("should update comment")
    void shouldUpdateComment() {
        Comment newComment = commentRepository.save(new Comment("New Comment")).block();
        newComment.setText("Updated Text");
        Comment expectedComment = commentRepository.save(newComment).block();

        String commentId = expectedComment.getId();
        StepVerifier.create(commentRepository.findById(commentId))
                .assertNext(comment ->
                        assertThat(comment)
                                .usingRecursiveComparison()
                                .ignoringExpectedNullFields()
                                .isEqualTo(expectedComment))
                .expectComplete()
                .verify();

        commentRepository.deleteById(commentId).block();
    }

    @Test
    @DisplayName("should delete comment by id")
    void shouldDeleteCommentById() {
        String savedCommentId = commentRepository.save(new Comment("New Comment")).block().getId();
        Book savedBook = bookRepository.save(new Book(null, "new book title", null, emptyList(), List.of(savedCommentId))).block();
        String savedBookId = savedBook.getId();

        StepVerifier.create(commentRepository.findById(savedCommentId))
                .assertNext(comment -> assertThat(comment).isNotNull())
                .expectComplete()
                .verify();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .assertNext(book -> assertThat(book).extracting(Book::getCommentIds).asList().contains(savedCommentId))
                .expectComplete()
                .verify();

        commentRepository.deleteWithRelationsById(savedCommentId).block();

        StepVerifier.create(commentRepository.findById(savedCommentId))
                .expectNextCount(0)
                .expectComplete()
                .verify();

        StepVerifier.create(bookRepository.findById(savedBookId))
                .assertNext(book -> assertThat(book).extracting(Book::getCommentIds).asList().doesNotContain(savedCommentId))
                .expectComplete()
                .verify();

        bookRepository.deleteById(savedBookId).block();
    }


    List<Comment> getExpectedComments() {
        return List.of(
                new Comment("Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."),
                new Comment("Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst."),
                new Comment("Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.")
        );
    }
}
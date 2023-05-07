package ru.otus.spring.hw12.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw12.domain.Comment;
import ru.otus.spring.hw12.repository.BookRepository;
import ru.otus.spring.hw12.repository.CommentRepository;
import ru.otus.spring.hw12.repository.cascade.CommentRepositoryCascade;
import ru.otus.spring.hw12.services.CommentsService;

import static org.mockito.Mockito.verify;

@DisplayName("Given comments service impl")
@SpringBootTest(classes = {CommentsServiceImpl.class})
class CommentsServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private CommentRepositoryCascade commentRepositoryCascade;

    @Autowired
    private CommentsService commentsService;

    @Test
    @DisplayName("should call CommentRepository to find all comments")
    void shouldCallCommentRepositoryToFindAllAuthors() {
        commentsService.findAll();
        verify(commentRepository).findAll();
    }

    @Test
    @DisplayName("should call CommentRepository to find comment by id")
    void shouldCallCommentRepositoryToFindAuthorById() {
        commentsService.findById("1");
        verify(commentRepository).findById("1");
    }

    @Test
    @DisplayName("should call BookRepository to find comments by book id")
    void shouldCallBookRepositoryToFindCommentsByBookId() {
        commentsService.findCommentsByBookId("1");
        verify(bookRepository).findById("1");
    }

    @Test
    @DisplayName("should call CommentRepository to save comment")
    void shouldCallCommentRepositoryToCreateAuthor() {
        Comment comment = new Comment("new comment");
        commentsService.save(comment);
        verify(commentRepository).save(comment);
    }

    @Test
    @DisplayName("should call CommentRepositoryCascade to delete comment by id")
    void shouldCallCommentRepositoryToDeleteAuthorById() {
        commentsService.deleteById("1");
        verify(commentRepositoryCascade).cascadeDeleteById("1");
    }
}
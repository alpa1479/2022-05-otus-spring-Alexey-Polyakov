package ru.otus.spring.hw07.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.domain.Comment;
import ru.otus.spring.hw07.repository.BookRepository;
import ru.otus.spring.hw07.repository.CommentRepository;
import ru.otus.spring.hw07.services.CommentsService;

import static org.mockito.Mockito.verify;

@DisplayName("Given comments service impl")
@SpringBootTest(classes = {CommentsServiceImpl.class})
class CommentsServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

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
        commentsService.findById(1);
        verify(commentRepository).findById(1L);
    }

    @Test
    @DisplayName("should call BookRepository to find comments by book id")
    void shouldCallBookRepositoryToFindCommentsByBookId() {
        commentsService.findCommentsByBookId(1L);
        verify(bookRepository).findById(1L);
    }

    @Test
    @DisplayName("should call CommentRepository to save comment")
    void shouldCallCommentRepositoryToCreateAuthor() {
        Comment comment = new Comment("new comment", new Book(1L));
        commentsService.save(comment);
        verify(commentRepository).save(comment);
    }

    @Test
    @DisplayName("should call CommentRepository to delete comment by id")
    void shouldCallCommentRepositoryToDeleteAuthorById() {
        commentsService.deleteById(1L);
        verify(commentRepository).deleteById(1L);
    }
}
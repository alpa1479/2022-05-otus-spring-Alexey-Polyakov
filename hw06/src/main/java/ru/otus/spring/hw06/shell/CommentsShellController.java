package ru.otus.spring.hw06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw06.domain.Comment;
import ru.otus.spring.hw06.services.CommentsService;
import ru.otus.spring.hw06.shell.argumentmappers.CommentArgumentMapper;

import java.util.List;

/*
    Example commands:
        c
        c_id 1
        c_c -b 1 -t new-comment
        u_c -i 1 -b 1 -t changed-text
        d_c_id 1
 */
@ShellComponent
@RequiredArgsConstructor
public class CommentsShellController {

    private final CommentsService commentsService;
    private final CommentArgumentMapper commentArgumentMapper;

    @ShellMethod(value = "Finds all comments", key = {"comments", "c"})
    public List<Comment> findAll() {
        return commentsService.findAll();
    }

    @ShellMethod(value = "Finds comments by book id", key = {"comments_by_book_id", "c_b_id"})
    public List<Comment> findByCommentsByBookId(@ShellOption("-i") long id) {
        return commentsService.findCommentsByBookId(id);
    }

    @ShellMethod(value = "Finds comment by id", key = {"comment_by_id", "c_id"})
    public Comment findById(@ShellOption("-i") long id) {
        return commentsService.findById(id);
    }

    @ShellMethod(value = "Creates comment", key = {"create_comment", "c_c"})
    public Comment create(@ShellOption("-b") long bookId, @ShellOption("-t") String text) {
        Comment comment = commentArgumentMapper.map(bookId, text);
        return commentsService.create(comment);
    }

    @ShellMethod(value = "Updates comment", key = {"update_comment", "u_c"})
    public Comment update(@ShellOption("-i") long id, @ShellOption("-b") long bookId, @ShellOption("-t") String text) {
        Comment comment = commentArgumentMapper.map(id, bookId, text);
        return commentsService.update(comment);
    }

    @ShellMethod(value = "Deletes comment by id", key = {"delete_comment_by_id", "d_c_id"})
    public void deleteById(@ShellOption("-i") long id) {
        commentsService.deleteById(id);
    }
}

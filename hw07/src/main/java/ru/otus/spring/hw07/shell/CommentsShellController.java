package ru.otus.spring.hw07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw07.domain.Comment;
import ru.otus.spring.hw07.services.CommentsService;
import ru.otus.spring.hw07.shell.argumentmappers.CommentArgumentMapper;
import ru.otus.spring.hw07.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;

/*
    Example commands:
        c
        c_b_id -i 1
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
    private final ToStringResponseMapper<Comment> commentToStringResponseMapper;

    @ShellMethod(value = "Finds all comments", key = {"comments", "c"})
    public String findAll() {
        return commentToStringResponseMapper.map(commentsService.findAll());
    }

    @ShellMethod(value = "Finds comments by book id", key = {"comments_by_book_id", "c_b_id"})
    public String findCommentsByBookId(@ShellOption("-i") long id) {
        return commentToStringResponseMapper.map(commentsService.findCommentsByBookId(id));
    }

    @ShellMethod(value = "Finds comment by id", key = {"comment_by_id", "c_id"})
    public String findById(@ShellOption("-i") long id) {
        return commentsService.findById(id)
                .map(commentToStringResponseMapper::map)
                .orElse(format("Can't find comment by id %s", id));
    }

    @ShellMethod(value = "Creates comment", key = {"create_comment", "c_c"})
    public String create(@ShellOption("-b") long bookId, @ShellOption("-t") String text) {
        Comment comment = commentArgumentMapper.map(bookId, text);
        return commentToStringResponseMapper.map(commentsService.save(comment));
    }

    @ShellMethod(value = "Updates comment", key = {"update_comment", "u_c"})
    public String update(@ShellOption("-i") long id, @ShellOption("-b") long bookId, @ShellOption("-t") String text) {
        Comment comment = commentArgumentMapper.map(id, bookId, text);
        return commentToStringResponseMapper.map(commentsService.save(comment));
    }

    @ShellMethod(value = "Deletes comment by id", key = {"delete_comment_by_id", "d_c_id"})
    public void deleteById(@ShellOption("-i") long id) {
        commentsService.deleteById(id);
    }
}

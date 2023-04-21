package ru.otus.spring.hw07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw07.domain.Book;
import ru.otus.spring.hw07.services.BooksService;
import ru.otus.spring.hw07.shell.argumentmappers.BookArgumentMapper;
import ru.otus.spring.hw07.shell.responsemappers.ToStringResponseMapper;

import java.util.List;

import static java.lang.String.format;

/*
    Example commands:
        b
        b_id 1
        c_b -t new-book -g 3 -a 1,2,3
        u_b -i 1 -t changed-title -g 5 -a 2,4,5
        u_b_t -i 1 -t new-title
        d_b_id 16
 */
@ShellComponent
@RequiredArgsConstructor
public class BooksShellController {

    private final BooksService booksService;
    private final BookArgumentMapper bookArgumentMapper;
    private final ToStringResponseMapper<Book> bookToStringResponseMapper;

    @ShellMethod(value = "Finds all books", key = {"books", "b"})
    public String findAll() {
        return bookToStringResponseMapper.map(booksService.findAll());
    }

    @ShellMethod(value = "Finds book by id", key = {"book_by_id", "b_id"})
    public String findById(@ShellOption("-i") long id) {
        return booksService.findById(id)
                .map(bookToStringResponseMapper::map)
                .orElse(format("Can't find book by id %s", id));
    }

    @ShellMethod(value = "Creates book", key = {"create_book", "c_b"})
    public String create(@ShellOption("-t") String title, @ShellOption("-g") long genreId,
                         @ShellOption("-a") List<Long> authorIds) {
        Book book = bookArgumentMapper.map(title, genreId, authorIds);
        return bookToStringResponseMapper.map(booksService.save(book));
    }

    @ShellMethod(value = "Updates book", key = {"update_book", "u_b"})
    public String update(@ShellOption("-i") long id, @ShellOption("-t") String title,
                         @ShellOption("-g") long genreId, @ShellOption("-a") List<Long> authorIds) {
        Book book = bookArgumentMapper.map(id, title, genreId, authorIds);
        return bookToStringResponseMapper.map(booksService.save(book));
    }

    @ShellMethod(value = "Updates book title", key = {"update_book_title", "u_b_t"})
    public void updateTitle(@ShellOption("-i") long id, @ShellOption("-t") String title) {
        booksService.updateBookTitleById(id, title);
    }

    @ShellMethod(value = "Deletes book by id", key = {"delete_book_by_id", "d_b_id"})
    public void deleteById(@ShellOption("-i") long id) {
        booksService.deleteById(id);
    }
}

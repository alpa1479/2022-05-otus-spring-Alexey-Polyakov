package ru.otus.spring.hw05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw05.domain.Book;
import ru.otus.spring.hw05.services.BooksService;
import ru.otus.spring.hw05.shell.argumentmappers.BookArgumentMapper;

import java.util.List;

/*
    Example commands:
        b
        b_id 1
        c_b -t new-book -g 3 -a 1,2,3
        u_b -i 1 -t changed-title -g 5 -a 2,4,5
        d_b_id 16
 */
@ShellComponent
@RequiredArgsConstructor
public class BooksShellController {

    private final BooksService booksService;
    private final BookArgumentMapper bookArgumentMapper;

    @ShellMethod(value = "Finds all books", key = {"books", "b"})
    public List<Book> findAll() {
        return booksService.findAll();
    }

    @ShellMethod(value = "Finds book by id", key = {"book_by_id", "b_id"})
    public Book findById(@ShellOption("-i") long id) {
        return booksService.findById(id);
    }

    @ShellMethod(value = "Creates book", key = {"create_book", "c_b"})
    public void create(@ShellOption("-t") String title, @ShellOption("-g") long genreId,
                       @ShellOption("-a") List<Long> authorIds) {
        Book book = bookArgumentMapper.map(title, genreId, authorIds);
        booksService.create(book);
    }

    @ShellMethod(value = "Updates book", key = {"update_book", "u_b"})
    public void update(@ShellOption("-i") long id, @ShellOption("-t") String title,
                       @ShellOption("-g") long genreId, @ShellOption("-a") List<Long> authorIds) {
        Book book = bookArgumentMapper.map(id, title, genreId, authorIds);
        booksService.update(book);
    }

    @ShellMethod(value = "Deletes book by id", key = {"delete_book_by_id", "d_b_id"})
    public void deleteById(@ShellOption("-i") long id) {
        booksService.deleteById(id);
    }
}

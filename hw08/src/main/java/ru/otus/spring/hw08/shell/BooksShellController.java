package ru.otus.spring.hw08.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.services.BooksService;
import ru.otus.spring.hw08.shell.argumentmappers.BookArgumentMapper;
import ru.otus.spring.hw08.shell.responsemappers.ToStringResponseMapper;

import java.util.List;

import static java.lang.String.format;

/*
    Example commands:
        b
        b_id 631f9a20e1435c2aedb32c9c
        c_b -t new-book -g 631f9c7589460655beea9185 -a 631f9c7589460655beea917c,631f9c7589460655beea917d,631f9c7589460655beea917e
        u_b -i 631f9c9789460655beea91c1 -t changed-title -g 631f9c7589460655beea9189 -a 631f9c7589460655beea917d
        u_b_t -i 631f9c9789460655beea91c1 -t new-title
        d_b_id 631f94c117dee66a064aea12
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
    public String findById(@ShellOption("-i") String id) {
        return booksService.findById(id)
                .map(bookToStringResponseMapper::map)
                .orElse(format("Can't find book by id %s", id));
    }

    @ShellMethod(value = "Creates book", key = {"create_book", "c_b"})
    public String create(@ShellOption("-t") String title, @ShellOption("-g") String genreId,
                         @ShellOption("-a") List<String> authorIds) {
        Book book = bookArgumentMapper.map(title, genreId, authorIds);
        return bookToStringResponseMapper.map(booksService.save(book));
    }

    @ShellMethod(value = "Updates book", key = {"update_book", "u_b"})
    public String update(@ShellOption("-i") String id, @ShellOption("-t") String title,
                         @ShellOption("-g") String genreId, @ShellOption("-a") List<String> authorIds) {
        Book book = bookArgumentMapper.map(id, title, genreId, authorIds);
        return bookToStringResponseMapper.map(booksService.save(book));
    }

    @ShellMethod(value = "Updates book title", key = {"update_book_title", "u_b_t"})
    public void updateTitle(@ShellOption("-i") String id, @ShellOption("-t") String title) {
        booksService.updateBookTitleById(id, title);
    }

    @ShellMethod(value = "Deletes book by id", key = {"delete_book_by_id", "d_b_id"})
    public void deleteById(@ShellOption("-i") String id) {
        booksService.deleteById(id);
    }
}

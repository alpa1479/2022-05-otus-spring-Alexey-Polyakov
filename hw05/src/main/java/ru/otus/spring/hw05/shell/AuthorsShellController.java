package ru.otus.spring.hw05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw05.domain.Author;
import ru.otus.spring.hw05.services.AuthorsService;
import ru.otus.spring.hw05.shell.argumentmappers.AuthorArgumentMapper;

import java.util.List;

/*
    Example commands:
        a
        a_id 1
        c_a new-author
        u_a -i 1 -n changed_name
        d_a_id 5
 */
@ShellComponent
@RequiredArgsConstructor
public class AuthorsShellController {

    private final AuthorsService authorsService;
    private final AuthorArgumentMapper authorArgumentMapper;

    @ShellMethod(value = "Finds all authors", key = {"authors", "a"})
    public List<Author> findAll() {
        return authorsService.findAll();
    }

    @ShellMethod(value = "Finds author by id", key = {"author_by_id", "a_id"})
    public Author findById(@ShellOption("-i") long id) {
        return authorsService.findById(id);
    }

    @ShellMethod(value = "Creates author", key = {"create_author", "c_a"})
    public void create(@ShellOption("-n") String name) {
        Author author = authorArgumentMapper.map(name);
        authorsService.create(author);
    }

    @ShellMethod(value = "Updates author", key = {"update_author", "u_a"})
    public void update(@ShellOption("-i") long id, @ShellOption("-n") String name) {
        Author author = authorArgumentMapper.map(id, name);
        authorsService.update(author);
    }

    @ShellMethod(value = "Deletes author by id", key = {"delete_author_by_id", "d_a_id"})
    public void deleteById(@ShellOption("-i") long id) {
        authorsService.deleteById(id);
    }
}

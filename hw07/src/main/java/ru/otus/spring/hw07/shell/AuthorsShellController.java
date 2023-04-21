package ru.otus.spring.hw07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw07.domain.Author;
import ru.otus.spring.hw07.services.AuthorsService;
import ru.otus.spring.hw07.shell.argumentmappers.AuthorArgumentMapper;
import ru.otus.spring.hw07.shell.responsemappers.ToStringResponseMapper;

import static java.lang.String.format;

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
    private final ToStringResponseMapper<Author> authorToStringResponseMapper;

    @ShellMethod(value = "Finds all authors", key = {"authors", "a"})
    public String findAll() {
        return authorToStringResponseMapper.map(authorsService.findAll());
    }

    @ShellMethod(value = "Finds author by id", key = {"author_by_id", "a_id"})
    public String findById(@ShellOption("-i") long id) {
        return authorsService.findById(id)
                .map(authorToStringResponseMapper::map)
                .orElse(format("Can't find author by id %s", id));
    }

    @ShellMethod(value = "Creates author", key = {"create_author", "c_a"})
    public String create(@ShellOption("-n") String name) {
        Author author = authorArgumentMapper.map(name);
        return authorToStringResponseMapper.map(authorsService.save(author));
    }

    @ShellMethod(value = "Updates author", key = {"update_author", "u_a"})
    public String update(@ShellOption("-i") long id, @ShellOption("-n") String name) {
        Author author = authorArgumentMapper.map(id, name);
        return authorToStringResponseMapper.map(authorsService.save(author));
    }

    @ShellMethod(value = "Deletes author by id", key = {"delete_author_by_id", "d_a_id"})
    public void deleteById(@ShellOption("-i") long id) {
        authorsService.deleteById(id);
    }
}

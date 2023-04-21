package ru.otus.spring.hw05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw05.domain.Genre;
import ru.otus.spring.hw05.services.GenresService;
import ru.otus.spring.hw05.shell.argumentmappers.GenreArgumentMapper;

import java.util.List;

/*
    Example commands:
        g
        g_id 1
        c_g new-genre
        u_g -i 1 -n changed_genre
        d_g_id 5
 */
@ShellComponent
@RequiredArgsConstructor
public class GenresShellController {

    private final GenresService genresService;
    private final GenreArgumentMapper genreArgumentMapper;

    @ShellMethod(value = "Finds all genres", key = {"genres", "g"})
    public List<Genre> findAll() {
        return genresService.findAll();
    }

    @ShellMethod(value = "Finds genre by id", key = {"genre_by_id", "g_id"})
    public Genre findById(@ShellOption("-i") long id) {
        return genresService.findById(id);
    }

    @ShellMethod(value = "Creates genre", key = {"create_genre", "c_g"})
    public void create(@ShellOption("-n") String name) {
        Genre genre = genreArgumentMapper.map(name);
        genresService.create(genre);
    }

    @ShellMethod(value = "Updates genre", key = {"update_genre", "u_g"})
    public void update(@ShellOption("-i") long id, @ShellOption("-n") String name) {
        Genre genre = genreArgumentMapper.map(id, name);
        genresService.update(genre);
    }

    @ShellMethod(value = "Deletes genre by id", key = {"delete_genre_by_id", "d_g_id"})
    public void deleteById(@ShellOption("-i") long id) {
        genresService.deleteById(id);
    }
}

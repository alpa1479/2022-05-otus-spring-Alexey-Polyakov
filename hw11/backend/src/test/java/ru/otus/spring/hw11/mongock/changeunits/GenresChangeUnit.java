package ru.otus.spring.hw11.mongock.changeunits;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw11.domain.Genre;
import ru.otus.spring.hw11.repository.GenreRepository;

import static java.util.List.of;

@RequiredArgsConstructor
@ChangeUnit(id = "GenresChangeUnit", order = "3")
public class GenresChangeUnit {

    private final GenreRepository genreRepository;

    @Execution
    public void saveGenres() {
        genreRepository.saveAll(of(
                new Genre("drama"),
                new Genre("horror"),
                new Genre("thriller")
        )).blockLast();
    }

    @RollbackExecution
    public void rollback() {
    }
}

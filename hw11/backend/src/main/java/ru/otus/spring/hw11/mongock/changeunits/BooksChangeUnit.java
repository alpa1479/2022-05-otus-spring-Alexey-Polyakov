package ru.otus.spring.hw11.mongock.changeunits;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Genre;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.GenreRepository;

import static java.util.List.of;

@RequiredArgsConstructor
@ChangeUnit(id = "BooksChangeUnit", order = "4")
public class BooksChangeUnit {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    // @formatter:off
    @Execution
    public void saveBooks() {
        bookRepository.saveAll(of(
                new Book("The Oblong Box",                  findGenreIdByName("drama")),
                new Book("Skin Deep",                       findGenreIdByName("horror")),
                new Book("Summer Catch",                    findGenreIdByName("thriller"))
        )).blockLast();
    }
    // @formatter:on

    @RollbackExecution
    public void rollback() {
    }

    private String findGenreIdByName(String name) {
        return genreRepository.findByName(name).map(Genre::getId).blockFirst();
    }
}

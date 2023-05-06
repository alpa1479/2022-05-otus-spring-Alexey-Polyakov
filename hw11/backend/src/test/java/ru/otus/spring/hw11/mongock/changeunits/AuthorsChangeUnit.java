package ru.otus.spring.hw11.mongock.changeunits;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.repository.AuthorRepository;

import static java.util.List.of;

@RequiredArgsConstructor
@ChangeUnit(id = "AuthorsChangeUnit", order = "2")
public class AuthorsChangeUnit {

    private final AuthorRepository authorRepository;

    @Execution
    public void saveAuthors() {
        authorRepository.saveAll(of(
                new Author("Thomas Artis"),
                new Author("Rania Martinez")
        )).blockLast();
    }

    @RollbackExecution
    public void rollback() {
    }
}

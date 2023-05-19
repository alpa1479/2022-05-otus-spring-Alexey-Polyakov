package ru.otus.spring.hw16.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.domain.excerpts.BookExcerpt;
import ru.otus.spring.hw16.repository.extension.BookRepositoryExtension;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = BookExcerpt.class)
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryExtension {

    @RestResource(exported = false)
    Optional<Book> findByTitle(String title);

    @Override
    void deleteById(String id);
}

package ru.otus.spring.hw07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.hw07.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @Query("select b from Book b join fetch b.genre")
    List<Book> findAll();

    @Override
    @Query("select b from Book b join fetch b.genre where b.id = :id")
    Optional<Book> findById(@Param("id") Long id);
}

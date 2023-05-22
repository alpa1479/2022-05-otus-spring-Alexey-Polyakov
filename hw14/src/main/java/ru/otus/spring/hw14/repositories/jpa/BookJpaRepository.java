package ru.otus.spring.hw14.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.hw14.domain.postgres.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    @Override
    @Query("select b from BookEntity b join fetch b.genre")
    List<BookEntity> findAll();

    @Override
    @Query("select b from BookEntity b join fetch b.genre where b.id = :id")
    Optional<BookEntity> findById(@Param("id") Long id);
}

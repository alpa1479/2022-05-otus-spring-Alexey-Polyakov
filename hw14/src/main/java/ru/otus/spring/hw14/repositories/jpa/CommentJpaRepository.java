package ru.otus.spring.hw14.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    @Override
    @Query("select new CommentEntity(c.id, b.id, b.title, c.text) from CommentEntity c join c.book b")
    List<CommentEntity> findAll();

    @Override
    @Query("select new CommentEntity(c.id, b.id, b.title, c.text) from CommentEntity c join c.book b where c.id = :id")
    Optional<CommentEntity> findById(@Param("id") Long id);
}

package ru.otus.spring.hw14.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
}

package ru.otus.spring.hw14.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;

public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, Long> {
}

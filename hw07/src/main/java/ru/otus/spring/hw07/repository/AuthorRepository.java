package ru.otus.spring.hw07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw07.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

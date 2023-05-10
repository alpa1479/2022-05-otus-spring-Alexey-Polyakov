package ru.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.repository.eager.CommentEagerRepository;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentEagerRepository {
}

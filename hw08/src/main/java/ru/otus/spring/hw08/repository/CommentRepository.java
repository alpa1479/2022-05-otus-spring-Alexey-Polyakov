package ru.otus.spring.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw08.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}

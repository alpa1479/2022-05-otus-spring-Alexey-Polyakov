package ru.otus.spring.hw16.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.repository.extension.CommentRepositoryExtension;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryExtension {

    @Override
    void deleteById(String id);
}

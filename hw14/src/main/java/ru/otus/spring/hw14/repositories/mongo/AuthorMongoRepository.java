package ru.otus.spring.hw14.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.domain.mongo.AuthorDocument;

public interface AuthorMongoRepository extends MongoRepository<AuthorDocument, String> {
}

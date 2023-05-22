package ru.otus.spring.hw14.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.domain.mongo.BookDocument;

public interface BookMongoRepository extends MongoRepository<BookDocument, String> {
}

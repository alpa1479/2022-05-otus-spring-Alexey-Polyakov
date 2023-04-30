package ru.otus.spring.hw09.repository.cascade.impl;

import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw09.domain.Book;
import ru.otus.spring.hw09.repository.AuthorRepository;
import ru.otus.spring.hw09.repository.cascade.AuthorRepositoryCascade;

@Service
@RequiredArgsConstructor
public class AuthorRepositoryCascadeImpl implements AuthorRepositoryCascade {

    private final MongoTemplate mongoTemplate;
    private final AuthorRepository authorRepository;

    @Override
    public void cascadeDeleteById(String id) {
        authorRepository.deleteById(id);
        deleteAuthorFromBooks(id);
    }

    private void deleteAuthorFromBooks(String id) {
        Query findBookWithAuthorQuery = new Query(Criteria.where("authors.$id").is(new ObjectId(id)));
        Update authorsCollectionUpdate = new Update().pull("authors", new BasicDBObject("$id", new ObjectId(id)));
        mongoTemplate.updateMulti(findBookWithAuthorQuery, authorsCollectionUpdate, Book.class);
    }
}

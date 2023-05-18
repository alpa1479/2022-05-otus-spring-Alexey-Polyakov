package ru.otus.spring.hw16.repository.extension.impl;

import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw16.domain.Author;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.repository.extension.AuthorRepositoryExtension;

@Service
@RequiredArgsConstructor
public class AuthorRepositoryExtensionImpl implements AuthorRepositoryExtension {

    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteById(String id) {
        deleteAuthor(id);
        deleteAuthorFromBooks(id);
    }

    private void deleteAuthor(String id) {
        Query findAuthorQuery = new Query(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.remove(findAuthorQuery, Author.class);
    }

    private void deleteAuthorFromBooks(String id) {
        Query findBookWithAuthorQuery = new Query(Criteria.where("authors.$id").is(new ObjectId(id)));
        Update authorsCollectionUpdate = new Update().pull("authors", new BasicDBObject("$id", new ObjectId(id)));
        mongoTemplate.updateMulti(findBookWithAuthorQuery, authorsCollectionUpdate, Book.class);
    }
}

package ru.otus.spring.hw16.repository.extension.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.repository.extension.BookRepositoryExtension;

@Service
@RequiredArgsConstructor
public class BookRepositoryExtensionImpl implements BookRepositoryExtension {

    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteById(String id) {
        deleteBook(id);
        deleteBookComments(id);
    }

    private void deleteBook(String id) {
        Query findBookQuery = new Query(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.remove(findBookQuery, Book.class);
    }

    private void deleteBookComments(String id) {
        Query findCommentsQuery = new Query(Criteria.where("book.$id").is(new ObjectId(id)));
        mongoTemplate.remove(findCommentsQuery, Comment.class);
    }
}

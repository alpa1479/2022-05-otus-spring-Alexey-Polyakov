package ru.otus.spring.hw08.repository.cascade.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Comment;
import ru.otus.spring.hw08.repository.BookRepository;
import ru.otus.spring.hw08.repository.cascade.BookRepositoryCascade;

@Service
@RequiredArgsConstructor
public class BookRepositoryCascadeImpl implements BookRepositoryCascade {

    private final MongoTemplate mongoTemplate;
    private final BookRepository bookRepository;

    @Override
    public void cascadeDeleteById(String id) {
        bookRepository.deleteById(id);
        deleteBookComments(id);
    }

    private void deleteBookComments(String id) {
        Query findCommentsQuery = new Query(Criteria.where("book.$id").is(new ObjectId(id)));
        mongoTemplate.remove(findCommentsQuery, Comment.class);
    }
}

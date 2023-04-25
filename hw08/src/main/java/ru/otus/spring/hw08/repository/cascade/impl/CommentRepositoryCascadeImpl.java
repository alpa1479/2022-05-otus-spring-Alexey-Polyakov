package ru.otus.spring.hw08.repository.cascade.impl;

import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.repository.CommentRepository;
import ru.otus.spring.hw08.repository.cascade.CommentRepositoryCascade;

@Service
@RequiredArgsConstructor
public class CommentRepositoryCascadeImpl implements CommentRepositoryCascade {

    private final MongoTemplate mongoTemplate;
    private final CommentRepository commentRepository;

    @Override
    public void cascadeDeleteById(String id) {
        commentRepository.deleteById(id);
        deleteCommentFromBook(id);
    }

    private void deleteCommentFromBook(String id) {
        Query findCommentsQuery = new Query(Criteria.where("comments.$id").is(new ObjectId(id)));
        Update commentsCollectionUpdate = new Update().pull("comments", new BasicDBObject("$id", new ObjectId(id)));
        mongoTemplate.updateFirst(findCommentsQuery, commentsCollectionUpdate, Book.class);
    }
}

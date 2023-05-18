package ru.otus.spring.hw16.repository.extension.impl;

import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.repository.extension.CommentRepositoryExtension;

@Service
@RequiredArgsConstructor
public class CommentRepositoryExtensionImpl implements CommentRepositoryExtension {

    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteById(String id) {
        deleteComment(id);
        deleteCommentFromBook(id);
    }

    @Override
    public void deleteByBookId(String bookId) {
        deleteComments(bookId);
        deleteCommentsFromBook(bookId);
    }

    private void deleteComment(String id) {
        Query findCommentQuery = new Query(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.remove(findCommentQuery, Comment.class);
    }

    private void deleteCommentFromBook(String id) {
        Query findCommentsQuery = new Query(Criteria.where("comments.$id").is(new ObjectId(id)));
        Update commentsCollectionUpdate = new Update().pull("comments", new BasicDBObject("$id", new ObjectId(id)));
        mongoTemplate.updateFirst(findCommentsQuery, commentsCollectionUpdate, Book.class);
    }

    private void deleteComments(String bookId) {
        Query findCommentsQuery = new Query(Criteria.where("book.$id").is(new ObjectId(bookId)));
        mongoTemplate.remove(findCommentsQuery, Comment.class);
    }

    private void deleteCommentsFromBook(String id) {
        Query findBookQuery = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Update unsetCommentsUpdate = new Update().unset("comments");
        mongoTemplate.updateFirst(findBookQuery, unsetCommentsUpdate, Book.class);
    }
}

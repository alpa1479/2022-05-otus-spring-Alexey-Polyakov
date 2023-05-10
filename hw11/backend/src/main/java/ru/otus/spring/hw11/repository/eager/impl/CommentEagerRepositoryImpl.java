package ru.otus.spring.hw11.repository.eager.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.repository.CommentRepository;
import ru.otus.spring.hw11.repository.eager.CommentEagerRepository;

@Service
public class CommentEagerRepositoryImpl implements CommentEagerRepository {

    private final CommentRepository commentRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public CommentEagerRepositoryImpl(@Lazy CommentRepository commentRepository,
                                      ReactiveMongoTemplate mongoTemplate) {
        this.commentRepository = commentRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Void> deleteWithRelationsById(String id) {
        return commentRepository.deleteById(id).and(deleteCommentFromBook(id));
    }

    @Override
    public Mono<Void> deleteWithRelationsByBookId(String bookId) {
        return deleteComments(bookId).and(deleteCommentsFromBook(bookId));
    }

    private Mono<UpdateResult> deleteCommentFromBook(String id) {
        Query findCommentsQuery = new Query(Criteria.where("commentIds").in(id));
        Update commentsCollectionUpdate = new Update().pull("commentIds", id);
        return mongoTemplate.updateFirst(findCommentsQuery, commentsCollectionUpdate, Book.class);
    }

    private Mono<UpdateResult> deleteCommentsFromBook(String id) {
        Query findBookQuery = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Update unsetCommentsUpdate = new Update().unset("commentIds");
        return mongoTemplate.updateFirst(findBookQuery, unsetCommentsUpdate, Book.class);
    }

    private Mono<DeleteResult> deleteComments(String bookId) {
        Query removeCommentsQuery = new Query(Criteria.where("bookId").is(bookId));
        return mongoTemplate.remove(removeCommentsQuery, Comment.class);
    }
}

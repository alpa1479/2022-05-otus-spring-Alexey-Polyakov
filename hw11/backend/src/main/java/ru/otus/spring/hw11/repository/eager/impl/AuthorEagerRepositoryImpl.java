package ru.otus.spring.hw11.repository.eager.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.repository.AuthorRepository;
import ru.otus.spring.hw11.repository.eager.AuthorEagerRepository;

@Service
public class AuthorEagerRepositoryImpl implements AuthorEagerRepository {

    private final AuthorRepository authorRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public AuthorEagerRepositoryImpl(@Lazy AuthorRepository authorRepository,
                                     ReactiveMongoTemplate mongoTemplate) {
        this.authorRepository = authorRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Void> deleteWithRelationsById(String id) {
        return authorRepository.deleteById(id).and(deleteAuthorFromBooks(id));
    }

    private Mono<UpdateResult> deleteAuthorFromBooks(String id) {
        Query findBookWithAuthorQuery = new Query(Criteria.where("authorIds").in(id));
        Update authorsCollectionUpdate = new Update().pull("authorIds", id);
        return mongoTemplate.updateMulti(findBookWithAuthorQuery, authorsCollectionUpdate, Book.class);
    }
}

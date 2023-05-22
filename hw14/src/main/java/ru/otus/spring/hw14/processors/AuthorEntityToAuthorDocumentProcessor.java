package ru.otus.spring.hw14.processors;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.domain.mongo.AuthorDocument;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;

@Service
@StepScope
public class AuthorEntityToAuthorDocumentProcessor implements ItemProcessor<AuthorEntity, AuthorDocument> {

    @Override
    public AuthorDocument process(AuthorEntity authorEntity) {
        return new AuthorDocument(authorEntity.getNewId(), authorEntity.getName());
    }
}

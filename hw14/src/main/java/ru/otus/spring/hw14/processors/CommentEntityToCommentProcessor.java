package ru.otus.spring.hw14.processors;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.domain.mongo.Comment;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;

@Service
@StepScope
public class CommentEntityToCommentProcessor implements ItemProcessor<CommentEntity, Comment> {

    @Override
    public Comment process(CommentEntity commentEntity) {
        return new Comment(commentEntity.getNewId(), commentEntity.getText());
    }
}

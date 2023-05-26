package ru.otus.spring.hw14.processors;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.domain.mongo.GenreDocument;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;

@Service
@StepScope
public class GenreEntityToGenreDocumentProcessor implements ItemProcessor<GenreEntity, GenreDocument> {

    @Override
    public GenreDocument process(GenreEntity genreEntity) {
        return new GenreDocument(genreEntity.getNewId(), genreEntity.getName());
    }
}

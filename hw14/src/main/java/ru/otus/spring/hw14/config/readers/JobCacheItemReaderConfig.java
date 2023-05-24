package ru.otus.spring.hw14.config.readers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;
import ru.otus.spring.hw14.domain.postgres.BookEntity;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;
import ru.otus.spring.hw14.readers.JobCacheItemReader;
import ru.otus.spring.hw14.services.JobCacheService;

@Configuration
@RequiredArgsConstructor
public class JobCacheItemReaderConfig {

    private final JobCacheService jobCacheService;

    @Bean
    @StepScope
    public ItemReader<BookEntity> bookCacheItemReader() {
        return new JobCacheItemReader<>(BookEntity.class, jobCacheService);
    }

    @Bean
    @StepScope
    public ItemReader<AuthorEntity> authorCacheItemReader() {
        return new JobCacheItemReader<>(AuthorEntity.class, jobCacheService);
    }

    @Bean
    @StepScope
    public ItemReader<GenreEntity> genreCacheItemReader() {
        return new JobCacheItemReader<>(GenreEntity.class, jobCacheService);
    }

    @Bean
    @StepScope
    public ItemReader<CommentEntity> commentCacheItemReader() {
        return new JobCacheItemReader<>(CommentEntity.class, jobCacheService);
    }
}

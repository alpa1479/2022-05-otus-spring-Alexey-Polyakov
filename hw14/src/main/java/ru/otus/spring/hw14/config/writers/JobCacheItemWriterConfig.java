package ru.otus.spring.hw14.config.writers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;
import ru.otus.spring.hw14.domain.postgres.BookEntity;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;
import ru.otus.spring.hw14.services.JobCacheService;
import ru.otus.spring.hw14.writers.JobCacheItemWriter;

@Configuration
@RequiredArgsConstructor
public class JobCacheItemWriterConfig {

    private final JobCacheService jobCacheService;

    @Bean
    @StepScope
    public ItemWriter<BookEntity> bookCacheItemWriter() {
        return new JobCacheItemWriter<>(BookEntity.class, jobCacheService);
    }

    @Bean
    @StepScope
    public ItemWriter<AuthorEntity> authorCacheItemWriter() {
        return new JobCacheItemWriter<>(AuthorEntity.class, jobCacheService);
    }

    @Bean
    @StepScope
    public ItemWriter<GenreEntity> genreCacheItemWriter() {
        return new JobCacheItemWriter<>(GenreEntity.class, jobCacheService);
    }

    @Bean
    @StepScope
    public ItemWriter<CommentEntity> commentCacheItemWriter() {
        return new JobCacheItemWriter<>(CommentEntity.class, jobCacheService);
    }
}

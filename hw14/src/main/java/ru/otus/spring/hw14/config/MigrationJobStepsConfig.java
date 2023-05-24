package ru.otus.spring.hw14.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.hw14.config.readers.JobCacheItemReaderConfig;
import ru.otus.spring.hw14.config.readers.JpaRepositoryItemReaderConfig;
import ru.otus.spring.hw14.config.writers.JobCacheItemWriterConfig;
import ru.otus.spring.hw14.config.writers.MongoRepositoryItemWriterConfig;
import ru.otus.spring.hw14.constants.StepType;
import ru.otus.spring.hw14.domain.EntityForMigration;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;
import ru.otus.spring.hw14.domain.postgres.BookEntity;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;
import ru.otus.spring.hw14.listener.ChunkLifecycleLogger;
import ru.otus.spring.hw14.processors.AuthorEntityToAuthorDocumentProcessor;
import ru.otus.spring.hw14.processors.BookEntityToBookDocumentProcessor;
import ru.otus.spring.hw14.processors.GenreEntityToGenreDocumentProcessor;
import ru.otus.spring.hw14.processors.IdentifierMigrationProcessor;
import ru.otus.spring.hw14.services.JobCleanUpService;

@Configuration
@RequiredArgsConstructor
public class MigrationJobStepsConfig {

    private static final int CHUNK_SIZE = 1;

    private final StepBuilderFactory stepBuilderFactory;
    private final JobCleanUpService jobCleanUpService;

    private final JpaRepositoryItemReaderConfig jpaRepositoryItemReaderConfig;
    private final JobCacheItemWriterConfig jobCacheItemWriterConfig;
    private final JobCacheItemReaderConfig jobCacheItemReaderConfig;
    private final MongoRepositoryItemWriterConfig mongoRepositoryItemWriterConfig;

    private final BookEntityToBookDocumentProcessor toBookDocumentProcessor;
    private final GenreEntityToGenreDocumentProcessor toGenreDocumentProcessor;
    private final AuthorEntityToAuthorDocumentProcessor toAuthorDocumentProcessor;

    // ------------------------------------ Extract steps start
    @Bean
    public Step extractBooksStep() {
        return createStep(
                StepType.EXTRACT,
                BookEntity.class,
                jpaRepositoryItemReaderConfig.bookRepositoryItemReader(),
                new IdentifierMigrationProcessor<>(),
                jobCacheItemWriterConfig.bookCacheItemWriter()
        );
    }

    @Bean
    public Step extractGenresStep() {
        return createStep(
                StepType.EXTRACT,
                GenreEntity.class,
                jpaRepositoryItemReaderConfig.genreRepositoryItemReader(),
                new IdentifierMigrationProcessor<>(),
                jobCacheItemWriterConfig.genreCacheItemWriter()
        );
    }

    @Bean
    public Step extractAuthorsStep() {
        return createStep(
                StepType.EXTRACT,
                AuthorEntity.class,
                jpaRepositoryItemReaderConfig.authorRepositoryItemReader(),
                new IdentifierMigrationProcessor<>(),
                jobCacheItemWriterConfig.authorCacheItemWriter()
        );
    }

    @Bean
    public Step extractCommentsStep() {
        return createStep(
                StepType.EXTRACT,
                CommentEntity.class,
                jpaRepositoryItemReaderConfig.commentRepositoryItemReader(),
                new IdentifierMigrationProcessor<>(),
                jobCacheItemWriterConfig.commentCacheItemWriter()
        );
    }
    // ------------------------------------ Extract steps end

    // ------------------------------------ Transform and load steps start
    @Bean
    public Step transformAndLoadBooksStep() {
        return createStep(
                StepType.TRANSFORM_AND_LOAD,
                BookEntity.class,
                jobCacheItemReaderConfig.bookCacheItemReader(),
                toBookDocumentProcessor,
                mongoRepositoryItemWriterConfig.bookRepositoryItemWriter()
        );
    }

    @Bean
    public Step transformAndLoadGenresStep() {
        return createStep(
                StepType.TRANSFORM_AND_LOAD,
                GenreEntity.class,
                jobCacheItemReaderConfig.genreCacheItemReader(),
                toGenreDocumentProcessor,
                mongoRepositoryItemWriterConfig.genreRepositoryItemWriter()
        );
    }

    @Bean
    public Step transformAndLoadAuthorsStep() {
        return createStep(
                StepType.TRANSFORM_AND_LOAD,
                AuthorEntity.class,
                jobCacheItemReaderConfig.authorCacheItemReader(),
                toAuthorDocumentProcessor,
                mongoRepositoryItemWriterConfig.authorRepositoryItemWriter()
        );
    }
    // ------------------------------------ Transform and load steps end

    @Bean
    public Step cleanUpStep() {
        var adapter = new MethodInvokingTaskletAdapter();
        adapter.setTargetObject(jobCleanUpService);
        adapter.setTargetMethod("cleanUp");
        return stepBuilderFactory.get("cleanUpStep")
                .tasklet(adapter)
                .build();
    }

    private <I extends EntityForMigration, O> Step createStep(StepType stepType,
                                                              Class<I> type,
                                                              ItemReader<I> reader,
                                                              ItemProcessor<I, O> processor,
                                                              ItemWriter<O> writer) {
        var stepName = type.getSimpleName() + stepType.getName();
        return stepBuilderFactory.get(stepName)
                .<I, O>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ChunkLifecycleLogger(stepName))
                .build();
    }

}

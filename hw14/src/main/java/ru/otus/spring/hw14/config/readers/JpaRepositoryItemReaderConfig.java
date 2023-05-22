package ru.otus.spring.hw14.config.readers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;
import ru.otus.spring.hw14.domain.postgres.BookEntity;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;
import ru.otus.spring.hw14.repositories.jpa.AuthorJpaRepository;
import ru.otus.spring.hw14.repositories.jpa.BookJpaRepository;
import ru.otus.spring.hw14.repositories.jpa.CommentJpaRepository;
import ru.otus.spring.hw14.repositories.jpa.GenreJpaRepository;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JpaRepositoryItemReaderConfig {

    private final BookJpaRepository bookRepository;
    private final GenreJpaRepository genreRepository;
    private final AuthorJpaRepository authorRepository;
    private final CommentJpaRepository commentRepository;

    @Bean
    @StepScope
    public ItemReader<BookEntity> bookRepositoryItemReader() {
        return getRepositoryItemReader(bookRepository);
    }

    @Bean
    @StepScope
    public ItemReader<GenreEntity> genreRepositoryItemReader() {
        return getRepositoryItemReader(genreRepository);
    }

    @Bean
    @StepScope
    public ItemReader<AuthorEntity> authorRepositoryItemReader() {
        return getRepositoryItemReader(authorRepository);
    }

    @Bean
    @StepScope
    public ItemReader<CommentEntity> commentRepositoryItemReader() {
        return getRepositoryItemReader(commentRepository);
    }

    private <T> ItemReader<T> getRepositoryItemReader(PagingAndSortingRepository<T, Long> repository) {
        return new RepositoryItemReaderBuilder<T>()
                .repository(repository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .saveState(false)
                .pageSize(10)
                .build();
    }
}

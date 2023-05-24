package ru.otus.spring.hw14.config.writers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.hw14.domain.mongo.AuthorDocument;
import ru.otus.spring.hw14.domain.mongo.BookDocument;
import ru.otus.spring.hw14.domain.mongo.GenreDocument;
import ru.otus.spring.hw14.repositories.mongo.AuthorMongoRepository;
import ru.otus.spring.hw14.repositories.mongo.BookMongoRepository;
import ru.otus.spring.hw14.repositories.mongo.GenreMongoRepository;

@Configuration
@RequiredArgsConstructor
public class MongoRepositoryItemWriterConfig {

    private final BookMongoRepository bookRepository;
    private final GenreMongoRepository genreRepository;
    private final AuthorMongoRepository authorRepository;

    @Bean
    @StepScope
    public ItemWriter<BookDocument> bookRepositoryItemWriter() {
        return getRepositoryItemWriter(bookRepository);
    }

    @Bean
    @StepScope
    public ItemWriter<GenreDocument> genreRepositoryItemWriter() {
        return getRepositoryItemWriter(genreRepository);
    }

    @Bean
    @StepScope
    public ItemWriter<AuthorDocument> authorRepositoryItemWriter() {
        return getRepositoryItemWriter(authorRepository);
    }

    private <T> ItemWriter<T> getRepositoryItemWriter(CrudRepository<T, String> repository) {
        return new RepositoryItemWriterBuilder<T>()
                .repository(repository)
                .build();
    }
}

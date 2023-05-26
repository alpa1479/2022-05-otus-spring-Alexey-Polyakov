package ru.otus.spring.hw14.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.domain.mongo.AuthorDocument;
import ru.otus.spring.hw14.domain.mongo.BookDocument;
import ru.otus.spring.hw14.domain.mongo.Comment;
import ru.otus.spring.hw14.domain.mongo.GenreDocument;
import ru.otus.spring.hw14.domain.postgres.AuthorEntity;
import ru.otus.spring.hw14.domain.postgres.BookEntity;
import ru.otus.spring.hw14.domain.postgres.CommentEntity;
import ru.otus.spring.hw14.domain.postgres.GenreEntity;
import ru.otus.spring.hw14.services.JobCacheService;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@StepScope
@RequiredArgsConstructor
public class BookEntityToBookDocumentProcessor implements ItemProcessor<BookEntity, BookDocument> {

    private final JobCacheService jobCacheService;
    private final GenreEntityToGenreDocumentProcessor toGenreDocumentProcessor;
    private final AuthorEntityToAuthorDocumentProcessor toAuthorDocumentProcessor;
    private final CommentEntityToCommentProcessor toCommentDocumentProcessor;

    @Override
    public BookDocument process(BookEntity bookEntity) {
        return BookDocument.builder()
                .id(bookEntity.getNewId())
                .title(bookEntity.getTitle())
                .genre(processGenre(bookEntity.getGenre()))
                .authors(processAuthors(bookEntity.getAuthors()))
                .comments(processComments(bookEntity.getComments()))
                .build();
    }

    @SuppressWarnings("unchecked")
    private GenreDocument processGenre(GenreEntity genreEntity) {
        var genres = (List<GenreEntity>) jobCacheService.get(GenreEntity.class.getSimpleName());
        return genres.stream()
                .filter(genre -> genre.getId().equals(genreEntity.getId()))
                .map(toGenreDocumentProcessor::process)
                .findAny()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    private List<AuthorDocument> processAuthors(List<AuthorEntity> authorEntities) {
        var authors = (List<AuthorEntity>) jobCacheService.get(AuthorEntity.class.getSimpleName());
        return authors.stream()
                .filter(author -> authorEntities.stream().anyMatch(authorEntity -> authorEntity.getId().equals(author.getId())))
                .map(toAuthorDocumentProcessor::process)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private List<Comment> processComments(List<CommentEntity> commentEntities) {
        var comments = (List<CommentEntity>) jobCacheService.get(CommentEntity.class.getSimpleName());
        return isEmpty(comments) ? emptyList() : comments.stream()
                .filter(comment -> commentEntities.stream().anyMatch(commentEntity -> commentEntity.getId().equals(comment.getId())))
                .map(toCommentDocumentProcessor::process)
                .toList();
    }
}

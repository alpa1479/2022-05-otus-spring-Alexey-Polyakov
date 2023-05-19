package ru.otus.spring.hw16.repository.extension.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw16.domain.Book;
import ru.otus.spring.hw16.domain.Comment;
import ru.otus.spring.hw16.domain.Genre;
import ru.otus.spring.hw16.repository.CommentRepository;
import ru.otus.spring.hw16.repository.extension.GenreRepositoryExtension;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreRepositoryExtensionImpl implements GenreRepositoryExtension {

    private final MongoTemplate mongoTemplate;
    private final CommentRepository commentRepository;

    @Override
    public void deleteById(String id) {
        deleteGenre(id);
        deleteBooks(id);
    }

    private void deleteGenre(String id) {
        Query findGenreQuery = new Query(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.remove(findGenreQuery, Genre.class);
    }

    private void deleteBooks(String id) {
        Query findBooksByGenreQuery = new Query(Criteria.where("genre.$id").is(new ObjectId(id)));
        List<String> commentsToDelete = mongoTemplate.findAllAndRemove(findBooksByGenreQuery, Book.class).stream()
                .map(Book::getComments)
                .flatMap(Collection::stream)
                .map(Comment::getId)
                .toList();
        commentRepository.deleteAllById(commentsToDelete);
    }
}

package ru.otus.spring.hw08.repository.cascade.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw08.domain.Book;
import ru.otus.spring.hw08.domain.Comment;
import ru.otus.spring.hw08.repository.CommentRepository;
import ru.otus.spring.hw08.repository.GenreRepository;
import ru.otus.spring.hw08.repository.cascade.GenreRepositoryCascade;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreRepositoryCascadeImpl implements GenreRepositoryCascade {

    private final MongoTemplate mongoTemplate;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public void cascadeDeleteById(String id) {
        genreRepository.deleteById(id);
        deleteBooks(id);
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

package ru.otus.spring.hw11.repository.eager.impl;

import com.mongodb.client.result.DeleteResult;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import ru.otus.spring.hw11.domain.Author;
import ru.otus.spring.hw11.domain.Book;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.domain.Genre;
import ru.otus.spring.hw11.repository.AuthorRepository;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.CommentRepository;
import ru.otus.spring.hw11.repository.GenreRepository;
import ru.otus.spring.hw11.repository.eager.BookEagerRepository;

import java.util.List;

@Service
public class BookEagerRepositoryImpl implements BookEagerRepository {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public BookEagerRepositoryImpl(@Lazy BookRepository bookRepository,
                                   AuthorRepository authorRepository,
                                   GenreRepository genreRepository,
                                   CommentRepository commentRepository,
                                   ReactiveMongoTemplate mongoTemplate) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Book> findAllWithRelations() {
        return bookRepository.findAll()
                .flatMap(book -> Mono.zip(
                        genreRepository.findById(book.getGenreId()),
                        authorRepository.findAllById(book.getAuthorIds()).collectList(),
                        commentRepository.findAllById(book.getCommentIds()).collectList()
                ).doOnNext(relations -> setBookRelations(book, relations)).thenReturn(book));
    }

    @Override
    public Mono<Book> findByIdWithRelations(String id) {
        return bookRepository.findById(id)
                .flatMap(book -> Mono.zip(
                        genreRepository.findById(book.getGenreId()),
                        authorRepository.findAllById(book.getAuthorIds()).collectList(),
                        commentRepository.findAllById(book.getCommentIds()).collectList()
                ).doOnNext(relations -> setBookRelations(book, relations)).thenReturn(book));
    }

    @Override
    public Mono<Void> deleteWithRelationsById(String id) {
        return bookRepository.deleteById(id).and(deleteBookComments(id));
    }

    private void setBookRelations(Book book, Tuple3<Genre, List<Author>, List<Comment>> relations) {
        book.setGenre(relations.getT1());
        book.setAuthors(relations.getT2());
        book.setComments(relations.getT3());
    }

    private Mono<DeleteResult> deleteBookComments(String id) {
        Query findCommentsQuery = new Query(Criteria.where("bookId").is(id));
        return mongoTemplate.remove(findCommentsQuery, Comment.class);
    }
}

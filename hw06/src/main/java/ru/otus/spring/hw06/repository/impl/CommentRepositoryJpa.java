package ru.otus.spring.hw06.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.domain.Comment;
import ru.otus.spring.hw06.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select new Comment(c.id, b.id, b.title, c.text) from Comment c join c.book b", Comment.class).getResultList();
    }

    @Override
    public Comment findById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public Comment create(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        return em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        em.remove(findById(id));
    }
}

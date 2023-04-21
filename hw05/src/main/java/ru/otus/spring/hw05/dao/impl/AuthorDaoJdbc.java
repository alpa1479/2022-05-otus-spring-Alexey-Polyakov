package ru.otus.spring.hw05.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.core.exception.EmptyKeyException;
import ru.otus.spring.hw05.dao.AuthorDao;
import ru.otus.spring.hw05.dao.impl.rowmappers.AuthorMapper;
import ru.otus.spring.hw05.domain.Author;

import java.util.Collection;
import java.util.List;

import static java.util.Map.of;
import static ru.otus.spring.hw05.core.util.Validations.requireNonNull;
import static ru.otus.spring.hw05.core.util.Validations.requireValue;

@Service
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations operations;

    @Override
    public List<Author> findAll() {
        return operations.query("select id, name from authors", new AuthorMapper());
    }

    @Override
    public List<Author> findByIds(Collection<Long> ids) {
        return operations.query("select id, name from authors where id in (:ids)", of("ids", ids), new AuthorMapper());
    }

    @Override
    public Author findById(long id) {
        return operations.queryForObject("select id, name from authors where id = :id", of("id", id), new AuthorMapper());
    }

    @Override
    public long create(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        operations.update("insert into authors (name) values (:name)",
                new MapSqlParameterSource(of("name", author.getName())), keyHolder, new String[]{"id"});
        return requireNonNull(keyHolder.getKey(), EmptyKeyException::new).longValue();
    }

    @Override
    public void update(Author author) {
        String sql = "update authors set name = :name where id = :id";
        int rowsAffected = operations.update(sql, of("id", author.getId(), "name", author.getName()));
        int expectedRowsAffected = 1;
        requireValue(rowsAffected, expectedRowsAffected, () -> new JdbcUpdateAffectedIncorrectNumberOfRowsException(sql, expectedRowsAffected, rowsAffected));
    }

    @Override
    public void deleteById(long id) {
        operations.update("delete from authors where id = :id", of("id", id));
    }
}

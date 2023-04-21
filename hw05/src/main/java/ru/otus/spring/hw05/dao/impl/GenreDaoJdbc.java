package ru.otus.spring.hw05.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.core.exception.EmptyKeyException;
import ru.otus.spring.hw05.dao.GenreDao;
import ru.otus.spring.hw05.dao.impl.rowmappers.GenreMapper;
import ru.otus.spring.hw05.domain.Genre;

import java.util.List;

import static java.util.Map.of;
import static ru.otus.spring.hw05.core.util.Validations.requireNonNull;
import static ru.otus.spring.hw05.core.util.Validations.requireValue;

@Service
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations operations;

    @Override
    public List<Genre> findAll() {
        return operations.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public Genre findById(long id) {
        return operations.queryForObject("select id, name from genres where id = :id", of("id", id), new GenreMapper());
    }

    @Override
    public long create(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        operations.update("insert into genres (name) values (:name)",
                new MapSqlParameterSource(of("name", genre.getName())), keyHolder, new String[]{"id"});
        return requireNonNull(keyHolder.getKey(), EmptyKeyException::new).longValue();
    }

    @Override
    public void update(Genre genre) {
        String sql = "update genres set name = :name where id = :id";
        int rowsAffected = operations.update(sql, of("id", genre.getId(), "name", genre.getName()));
        int expectedRowsAffected = 1;
        requireValue(rowsAffected, expectedRowsAffected, () -> new JdbcUpdateAffectedIncorrectNumberOfRowsException(sql, expectedRowsAffected, rowsAffected));
    }

    @Override
    public void deleteById(long id) {
        operations.update("delete from genres where id = :id", of("id", id));
    }
}

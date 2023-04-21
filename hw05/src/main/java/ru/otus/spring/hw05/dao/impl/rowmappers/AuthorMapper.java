package ru.otus.spring.hw05.dao.impl.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.hw05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(rs.getLong("id"), rs.getString("name"));
    }
}

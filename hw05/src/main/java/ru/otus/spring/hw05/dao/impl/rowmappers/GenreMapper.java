package ru.otus.spring.hw05.dao.impl.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Genre(rs.getLong("id"), rs.getString("name"));
    }
}

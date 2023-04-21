package ru.otus.spring.hw05.dao.impl.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.hw05.domain.Book;
import ru.otus.spring.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("id"), rs.getString("title"),
                new Genre(rs.getLong("genre_id"), rs.getString("genre_name")));
    }

}

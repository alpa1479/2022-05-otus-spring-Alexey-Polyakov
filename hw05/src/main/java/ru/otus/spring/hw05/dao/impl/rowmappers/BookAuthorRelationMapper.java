package ru.otus.spring.hw05.dao.impl.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.hw05.dao.impl.relations.BookAuthorRelation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorRelationMapper implements RowMapper<BookAuthorRelation> {

    @Override
    public BookAuthorRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookAuthorRelation(rs.getLong("book_id"), rs.getLong("author_id"));
    }
}

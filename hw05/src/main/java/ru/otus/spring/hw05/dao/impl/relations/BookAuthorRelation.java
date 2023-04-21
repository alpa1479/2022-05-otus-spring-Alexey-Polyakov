package ru.otus.spring.hw05.dao.impl.relations;

import lombok.Data;

@Data
public class BookAuthorRelation {

    private final long bookId;
    private final long authorId;
}

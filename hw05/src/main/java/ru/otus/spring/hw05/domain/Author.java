package ru.otus.spring.hw05.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {

    private final long id;
    private final String name;

    public Author(long id) {
        this.id = id;
        this.name = null;
    }

    public Author(String name) {
        this.id = 0;
        this.name = name;
    }
}

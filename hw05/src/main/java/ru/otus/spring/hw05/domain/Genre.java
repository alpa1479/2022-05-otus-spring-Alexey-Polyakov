package ru.otus.spring.hw05.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Genre {

    private final long id;
    private final String name;

    public Genre(long id) {
        this.id = id;
        this.name = null;
    }

    public Genre(String name) {
        this.id = 0;
        this.name = name;
    }
}

package ru.otus.spring.hw03.domain;

import lombok.Data;

@Data
public class Answer {

    private final String value;
    private final boolean correct;
}

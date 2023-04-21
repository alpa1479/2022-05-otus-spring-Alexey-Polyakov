package ru.otus.spring.hw04.domain;

import lombok.Data;

@Data
public class Answer {

    private final String value;
    private final boolean correct;
}

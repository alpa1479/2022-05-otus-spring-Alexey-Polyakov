package ru.otus.spring.hw01.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private final String value;
    private final List<String> answers;
}

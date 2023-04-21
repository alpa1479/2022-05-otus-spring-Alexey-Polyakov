package ru.otus.spring.hw01.services;

import ru.otus.spring.hw01.domain.Question;

import java.util.List;

public interface QuestionsReader {

    List<Question> read();
}

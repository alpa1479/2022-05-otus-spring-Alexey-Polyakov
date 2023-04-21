package ru.otus.spring.hw01.services;

import ru.otus.spring.hw01.domain.Question;

import java.util.List;

public interface QuestionsWriter {

    void write(List<Question> questions);
}

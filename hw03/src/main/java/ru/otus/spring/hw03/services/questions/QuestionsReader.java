package ru.otus.spring.hw03.services.questions;

import ru.otus.spring.hw03.domain.Question;

import java.util.List;

public interface QuestionsReader {

    List<Question> read();
}

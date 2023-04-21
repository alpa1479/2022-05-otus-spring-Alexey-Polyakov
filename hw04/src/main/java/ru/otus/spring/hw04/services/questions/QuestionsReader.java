package ru.otus.spring.hw04.services.questions;

import ru.otus.spring.hw04.domain.Question;

import java.util.List;

public interface QuestionsReader {

    List<Question> read();
}

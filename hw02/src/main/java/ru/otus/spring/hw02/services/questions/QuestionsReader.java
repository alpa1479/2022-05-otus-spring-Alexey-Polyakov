package ru.otus.spring.hw02.services.questions;

import ru.otus.spring.hw02.domain.Question;

import java.util.List;

public interface QuestionsReader {

    List<Question> read();
}

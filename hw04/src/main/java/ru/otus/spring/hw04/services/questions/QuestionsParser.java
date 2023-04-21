package ru.otus.spring.hw04.services.questions;

import ru.otus.spring.hw04.domain.Question;

import java.util.List;

public interface QuestionsParser {

    List<Question> parse(List<String> lines);

    Question parse(String line);
}

package ru.otus.spring.hw01.services.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.services.QuestionsWriter;

import java.io.PrintStream;
import java.util.List;

@RequiredArgsConstructor
public class ConsoleWriter implements QuestionsWriter {

    private final PrintStream out;

    @Override
    public void write(List<Question> questions) {
        out.println("Java survey, please choose one possible answer from the questions:");
        for (int index = 0; index < questions.size(); index++) {
            Question question = questions.get(index);
            writeQuestionValue(question.getValue(), index);
            writeAnswers(question.getAnswers());
        }
    }

    private void writeQuestionValue(String value, int index) {
        out.printf("%d. %s%n", index + 1, value);
    }

    private void writeAnswers(List<String> answers) {
        for (String answer : answers) {
            out.printf("\t- %s%n", answer);
        }
    }
}

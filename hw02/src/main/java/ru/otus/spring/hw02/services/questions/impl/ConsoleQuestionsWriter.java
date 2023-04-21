package ru.otus.spring.hw02.services.questions.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.io.InputOutputService;
import ru.otus.spring.hw02.services.questions.QuestionsWriter;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ConsoleQuestionsWriter implements QuestionsWriter {

    private final InputOutputService io;

    @Override
    public void write(Question question) {
        writeQuestionValue(question.getValue(), question.getOrder());
        writeAnswers(question.getAnswers());
    }

    private void writeQuestionValue(String value, int order) {
        io.write(format("%d. %s%n", order, value));
    }

    private void writeAnswers(List<Answer> answers) {
        for (int index = 0; index < answers.size(); index++) {
            Answer answer = answers.get(index);
            io.write(format("\t%d. %s%n", index + 1, answer.getValue()));
        }
    }
}

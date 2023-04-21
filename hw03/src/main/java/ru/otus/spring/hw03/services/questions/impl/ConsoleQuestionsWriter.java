package ru.otus.spring.hw03.services.questions.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Question;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.io.InputOutputService;
import ru.otus.spring.hw03.services.questions.QuestionsWriter;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ConsoleQuestionsWriter implements QuestionsWriter {

    private final I18nService i18n;
    private final InputOutputService io;

    @Override
    public void write(Question question) {
        writeQuestionValue(question.getValue(), question.getOrder());
        writeAnswers(question.getAnswers());
    }

    private void writeQuestionValue(String value, int order) {
        io.write(format("%d. %s%n", order, i18n.getMessage(value)));
    }

    private void writeAnswers(List<Answer> answers) {
        for (int index = 0; index < answers.size(); index++) {
            Answer answer = answers.get(index);
            io.write(format("\t%d. %s%n", index + 1, i18n.getMessage(answer.getValue())));
        }
    }
}

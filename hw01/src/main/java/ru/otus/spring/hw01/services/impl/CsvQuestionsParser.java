package ru.otus.spring.hw01.services.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.exception.IncorrectQuestionFormatException;
import ru.otus.spring.hw01.services.QuestionsParser;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static ru.otus.spring.hw01.util.Validations.requireSize;

@RequiredArgsConstructor
public class CsvQuestionsParser implements QuestionsParser {

    private static final int VALID_SPLIT_LINE_LENGTH = 2;

    private final String questionsSeparator;
    private final String answersSeparator;

    @Override
    public List<Question> parse(List<String> lines) {
        return lines.stream().skip(1).map(this::parse).toList();
    }

    @Override
    public Question parse(String line) {
        String[] split = line.split(questionsSeparator);
        requireSize(split, VALID_SPLIT_LINE_LENGTH,
                () -> new IncorrectQuestionFormatException(format("Question or answers are missing, please check format for - [%s]", line)));
        String questionValue = split[0];
        String answersLine = split[1];
        String[] answers = answersLine.split(answersSeparator);
        return new Question(questionValue, Arrays.asList(answers));
    }
}

package ru.otus.spring.hw03.services.answers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.config.StudentsSurveyApplicationProperties;
import ru.otus.spring.hw03.config.properties.AnswerProperties;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;
import ru.otus.spring.hw03.services.answers.AnswersChecker;

import java.util.List;

@Service
public class AnswersCheckerImpl implements AnswersChecker {

    private final AnswerProperties answerProperties;

    public AnswersCheckerImpl(StudentsSurveyApplicationProperties properties) {
        this.answerProperties = properties.getAnswers();
    }

    @Override
    public SurveyResult check(Student student, List<Answer> answers) {
        long correctAnswersCount = answers.stream().filter(Answer::isCorrect).count();
        return new SurveyResult(student, correctAnswersCount >= answerProperties.getCountToPass(), correctAnswersCount);
    }
}

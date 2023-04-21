package ru.otus.spring.hw02.services.answers.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.answers.AnswersChecker;

import java.util.List;

@Service
public class AnswersCheckerImpl implements AnswersChecker {

    private final int countOfCorrectAnswersToPass;

    public AnswersCheckerImpl(@Value("${answers.count-to-pass}") int countOfCorrectAnswersToPass) {
        this.countOfCorrectAnswersToPass = countOfCorrectAnswersToPass;
    }

    @Override
    public SurveyResult check(Student student, List<Answer> answers) {
        long correctAnswersCount = answers.stream().filter(Answer::isCorrect).count();
        return new SurveyResult(student, correctAnswersCount >= countOfCorrectAnswersToPass, correctAnswersCount);
    }
}

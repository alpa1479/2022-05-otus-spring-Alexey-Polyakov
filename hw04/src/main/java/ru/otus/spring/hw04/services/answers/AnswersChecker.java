package ru.otus.spring.hw04.services.answers;

import ru.otus.spring.hw04.domain.Answer;
import ru.otus.spring.hw04.domain.Student;
import ru.otus.spring.hw04.domain.SurveyResult;

import java.util.List;

public interface AnswersChecker {

    SurveyResult check(Student student, List<Answer> answers);
}

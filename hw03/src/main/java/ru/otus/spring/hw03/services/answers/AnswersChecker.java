package ru.otus.spring.hw03.services.answers;

import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;

import java.util.List;

public interface AnswersChecker {

    SurveyResult check(Student student, List<Answer> answers);
}

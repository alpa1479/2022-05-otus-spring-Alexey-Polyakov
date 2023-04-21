package ru.otus.spring.hw02.services.answers;

import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;

import java.util.List;

public interface AnswersChecker {

    SurveyResult check(Student student, List<Answer> answers);
}

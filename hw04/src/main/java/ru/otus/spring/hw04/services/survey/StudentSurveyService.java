package ru.otus.spring.hw04.services.survey;

import ru.otus.spring.hw04.domain.Student;
import ru.otus.spring.hw04.domain.SurveyResult;

public interface StudentSurveyService {

    SurveyResult start(Student student);
}

package ru.otus.spring.hw03.services.survey;

import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;

public interface StudentSurveyService {

    SurveyResult start(Student student);
}

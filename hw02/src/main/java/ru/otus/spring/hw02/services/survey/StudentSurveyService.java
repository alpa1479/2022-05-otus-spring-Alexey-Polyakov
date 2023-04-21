package ru.otus.spring.hw02.services.survey;

import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;

public interface StudentSurveyService {

    SurveyResult start(Student student);
}

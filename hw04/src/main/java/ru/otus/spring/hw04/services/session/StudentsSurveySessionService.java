package ru.otus.spring.hw04.services.session;

import ru.otus.spring.hw04.domain.Student;
import ru.otus.spring.hw04.domain.SurveyResult;

public interface StudentsSurveySessionService {

    Student getLoggedInStudent();

    void setLoggedInStudent(Student student);

    SurveyResult getLatestSurveyResult();

    void setLatestSurveyResult(SurveyResult result);
}

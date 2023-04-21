package ru.otus.spring.hw04.services.session.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.domain.Student;
import ru.otus.spring.hw04.domain.SurveyResult;
import ru.otus.spring.hw04.services.session.StudentsSurveySessionService;

@Service
public class InMemoryStudentsSurveySessionService implements StudentsSurveySessionService {

    private Student loggedInStudent;
    private SurveyResult latestSurveyResult;

    @Override
    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    @Override
    public void setLoggedInStudent(Student student) {
        loggedInStudent = student;
    }

    @Override
    public SurveyResult getLatestSurveyResult() {
        return latestSurveyResult;
    }

    @Override
    public void setLatestSurveyResult(SurveyResult result) {
        latestSurveyResult = result;
    }
}

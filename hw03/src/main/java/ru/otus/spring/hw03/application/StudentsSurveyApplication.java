package ru.otus.spring.hw03.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;
import ru.otus.spring.hw03.services.students.GreetingService;
import ru.otus.spring.hw03.services.students.StudentIdentificationService;
import ru.otus.spring.hw03.services.students.StudentInputService;
import ru.otus.spring.hw03.services.survey.StudentSurveyService;
import ru.otus.spring.hw03.services.survey.SurveyResultWriter;

@Service
@RequiredArgsConstructor
public class StudentsSurveyApplication {

    private final GreetingService greetingService;
    private final SurveyResultWriter surveyResultWriter;
    private final StudentInputService studentInputService;
    private final StudentSurveyService studentSurveyService;
    private final StudentIdentificationService studentIdentificationService;

    public void run() {
        do {
            greetingService.greet();
            Student student = studentIdentificationService.identify();
            SurveyResult surveyResult = studentSurveyService.start(student);
            surveyResultWriter.write(surveyResult);
        } while (studentInputService.askContinue());
    }
}

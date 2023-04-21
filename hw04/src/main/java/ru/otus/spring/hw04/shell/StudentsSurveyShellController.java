package ru.otus.spring.hw04.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.hw04.domain.Student;
import ru.otus.spring.hw04.domain.SurveyResult;
import ru.otus.spring.hw04.services.session.StudentsSurveySessionService;
import ru.otus.spring.hw04.services.students.GreetingService;
import ru.otus.spring.hw04.services.students.StudentIdentificationService;
import ru.otus.spring.hw04.services.survey.StudentSurveyService;
import ru.otus.spring.hw04.services.survey.SurveyResultWriter;

import static org.springframework.shell.Availability.available;
import static org.springframework.shell.Availability.unavailable;

@ShellComponent
@RequiredArgsConstructor
public class StudentsSurveyShellController {

    private final GreetingService greetingService;
    private final SurveyResultWriter surveyResultWriter;
    private final StudentSurveyService studentSurveyService;
    private final StudentsSurveySessionService sessionService;
    private final StudentIdentificationService studentIdentificationService;

    @ShellMethod(value = "Starts login procedure to identify student", key = {"login", "log", "l"})
    public void loginStudent() {
        greetingService.greet();
        Student student = studentIdentificationService.identify();
        sessionService.setLoggedInStudent(student);
        sessionService.setLatestSurveyResult(null);
    }

    @ShellMethod(value = "Starts survey for logged in student", key = {"start", "s"})
    public void startSurvey() {
        Student student = sessionService.getLoggedInStudent();
        SurveyResult result = studentSurveyService.start(student);
        sessionService.setLatestSurveyResult(result);
    }

    @ShellMethod(value = "Displays latest result of the survey for logged in student", key = {"result", "res", "r"})
    public void displaySurveyResult() {
        SurveyResult surveyResult = sessionService.getLatestSurveyResult();
        surveyResultWriter.write(surveyResult);
    }

    private Availability startSurveyAvailability() {
        return sessionService.getLoggedInStudent() == null
                ? unavailable("you should execute command 'login' and provide your details first")
                : available();
    }

    private Availability displaySurveyResultAvailability() {
        Student student = sessionService.getLoggedInStudent();
        if (student == null) {
            return unavailable("you should execute command 'login' and provide your details first");
        }
        SurveyResult latestSurveyResult = sessionService.getLatestSurveyResult();
        if (latestSurveyResult == null) {
            return unavailable("you should execute command 'start' and answer all questions first");
        }
        return available();
    }
}

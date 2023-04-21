package ru.otus.spring.hw04.services.survey.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.domain.Answer;
import ru.otus.spring.hw04.domain.Question;
import ru.otus.spring.hw04.domain.Student;
import ru.otus.spring.hw04.domain.SurveyResult;
import ru.otus.spring.hw04.services.answers.AnswersChecker;
import ru.otus.spring.hw04.services.questions.QuestionsReader;
import ru.otus.spring.hw04.services.students.GreetingService;
import ru.otus.spring.hw04.services.students.StudentInputService;
import ru.otus.spring.hw04.services.survey.StudentSurveyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSurveyServiceImpl implements StudentSurveyService {

    private final AnswersChecker answersChecker;
    private final GreetingService greetingService;
    private final QuestionsReader questionsReader;
    private final StudentInputService studentInputService;

    @Override
    public SurveyResult start(Student student) {
        List<Question> questions = questionsReader.read();
        greetingService.greet(student, questions.size());
        List<Answer> answers = questions.stream().map(studentInputService::askQuestion).toList();
        return answersChecker.check(student, answers);
    }
}

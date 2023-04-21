package ru.otus.spring.hw02.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.io.InputOutputService;
import ru.otus.spring.hw02.services.questions.QuestionsWriter;
import ru.otus.spring.hw02.services.students.StudentInputService;
import ru.otus.spring.hw02.services.students.StudentInputValidationService;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StudentInputServiceImpl implements StudentInputService {

    private final InputOutputService io;
    private final QuestionsWriter questionsWriter;
    private final StudentInputValidationService studentInputValidationService;

    @Override
    public String askName(String type) {
        io.write(format("%s - ", type));
        String name = io.readLine();
        while (!studentInputValidationService.validateStudentName(name)) {
            io.write(format("Incorrect format of input - [%s], please try again.%n", name));
            io.write(format("%s - ", type));
            name = io.readLine();
        }
        return name;
    }

    @Override
    public Answer askQuestion(Question question) {
        questionsWriter.write(question);
        io.write("Please choose the number of your answer - ");
        String indexOfAnswer = io.readLine();
        while (!studentInputValidationService.validateSelectedAnswer(indexOfAnswer, question.getAnswersSize())) {
            io.write(format("Incorrect format of input - [%s], please try again.%n", indexOfAnswer));
            io.write("Please choose the number of your answer - ");
            indexOfAnswer = io.readLine();
        }
        return question.getAnswer(Integer.parseInt(indexOfAnswer) - 1);
    }

    @Override
    public boolean askContinue() {
        io.write("Do you want to start the survey again? please enter - y/n\n");
        String continueCommand = io.readLine();
        while (!studentInputValidationService.validateContinueCommand(continueCommand)) {
            io.write("Incorrect format of input, please try again.\n");
            io.write("Please enter - y/n\n");
            continueCommand = io.readLine();
        }
        return continueCommand.equalsIgnoreCase("y");
    }
}

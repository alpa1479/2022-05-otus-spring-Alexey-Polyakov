package ru.otus.spring.hw02.services.students;

public interface StudentInputValidationService {

    boolean validateStudentName(String name);

    boolean validateSelectedAnswer(String selectedAnswer, int answersSize);

    boolean validateContinueCommand(String continueCommand);
}

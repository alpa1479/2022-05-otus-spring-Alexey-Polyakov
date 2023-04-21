package ru.otus.spring.hw04.services.students;

public interface StudentInputValidationService {

    boolean validateStudentName(String name);

    boolean validateSelectedAnswer(String selectedAnswer, int answersSize);
}

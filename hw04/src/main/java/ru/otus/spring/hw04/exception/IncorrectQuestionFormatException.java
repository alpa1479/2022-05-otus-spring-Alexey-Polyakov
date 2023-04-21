package ru.otus.spring.hw04.exception;

public class IncorrectQuestionFormatException extends StudentsSurveyApplicationException {

    public IncorrectQuestionFormatException(String message) {
        super(message);
    }
}

package ru.otus.spring.hw02.exception;

public class IncorrectQuestionFormatException extends StudentsSurveyApplicationException {

    public IncorrectQuestionFormatException(String message) {
        super(message);
    }
}

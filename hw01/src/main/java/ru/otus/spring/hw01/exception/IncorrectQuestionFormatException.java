package ru.otus.spring.hw01.exception;

public class IncorrectQuestionFormatException extends StudentsSurveyApplicationException {

    public IncorrectQuestionFormatException(String message) {
        super(message);
    }
}

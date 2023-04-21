package ru.otus.spring.hw03.exception;

public class IncorrectQuestionFormatException extends StudentsSurveyApplicationException {

    public IncorrectQuestionFormatException(String message) {
        super(message);
    }
}

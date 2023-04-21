package ru.otus.spring.hw03.exception;

public class ResourceFileNotFoundException extends StudentsSurveyApplicationException {

    public ResourceFileNotFoundException(String message) {
        super(message);
    }
}

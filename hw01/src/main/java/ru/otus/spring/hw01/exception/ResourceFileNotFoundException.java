package ru.otus.spring.hw01.exception;

public class ResourceFileNotFoundException extends StudentsSurveyApplicationException {

    public ResourceFileNotFoundException(String message) {
        super(message);
    }
}

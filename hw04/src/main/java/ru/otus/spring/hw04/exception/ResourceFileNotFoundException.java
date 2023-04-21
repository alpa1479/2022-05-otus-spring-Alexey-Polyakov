package ru.otus.spring.hw04.exception;

public class ResourceFileNotFoundException extends StudentsSurveyApplicationException {

    public ResourceFileNotFoundException(String message) {
        super(message);
    }
}

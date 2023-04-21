package ru.otus.spring.hw02.exception;

public class ResourceFileNotFoundException extends StudentsSurveyApplicationException {

    public ResourceFileNotFoundException(String message) {
        super(message);
    }
}

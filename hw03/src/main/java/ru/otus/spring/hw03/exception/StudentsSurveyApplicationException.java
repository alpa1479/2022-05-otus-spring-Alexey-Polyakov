package ru.otus.spring.hw03.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StudentsSurveyApplicationException extends RuntimeException {

    public StudentsSurveyApplicationException(String message) {
        super(message);
    }
}

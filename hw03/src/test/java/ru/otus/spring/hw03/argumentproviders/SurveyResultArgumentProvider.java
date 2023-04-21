package ru.otus.spring.hw03.argumentproviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class SurveyResultArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Student firstStudent = new Student("First", "Student");
        Student secondStudent = new Student("Second", "Student");
        Student thirdStudent = new Student("Third", "Student");
        return Stream.of(
                of(firstStudent, List.of(
                        new Answer("one", true),
                        new Answer("three", true),
                        new Answer("five", false),
                        new Answer("seven", false),
                        new Answer("nine", false)
                ), new SurveyResult(firstStudent, false, 2)),
                of(secondStudent, List.of(
                        new Answer("one", true),
                        new Answer("three", true),
                        new Answer("five", true),
                        new Answer("seven", true),
                        new Answer("nine", false)
                ), new SurveyResult(secondStudent, true, 4)),
                of(thirdStudent, List.of(
                        new Answer("one", true),
                        new Answer("three", true),
                        new Answer("five", true),
                        new Answer("seven", true),
                        new Answer("nine", true)
                ), new SurveyResult(thirdStudent, true, 5))
        );
    }
}

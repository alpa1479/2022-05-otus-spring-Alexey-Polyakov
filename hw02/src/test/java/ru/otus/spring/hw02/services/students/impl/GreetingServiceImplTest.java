package ru.otus.spring.hw02.services.students.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.services.io.InputOutputService;

import static org.mockito.AdditionalMatchers.and;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

@MockitoSettings
@DisplayName("Given greeting service")
class GreetingServiceImplTest {

    @Mock
    private InputOutputService io;

    @InjectMocks
    private GreetingServiceImpl greetingService;

    @Test
    @DisplayName("when getting student with amount of questions, then write student full name and amount of questions")
    void when_GettingStudentWithAmountOfQuestions_then_WriteStudentFullNameAndAmountOfQuestions() {
        // when
        int numberOfQuestions = 5;
        Student student = getStudent();
        greetingService.greet(student, numberOfQuestions);

        // then
        verify(io).write(and(contains(student.getFullName()), contains(String.valueOf(numberOfQuestions))));
    }

    Student getStudent() {
        return new Student("Test", "Student");
    }
}

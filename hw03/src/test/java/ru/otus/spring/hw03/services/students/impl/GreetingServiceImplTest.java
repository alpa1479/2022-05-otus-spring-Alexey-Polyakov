package ru.otus.spring.hw03.services.students.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.io.InputOutputService;
import ru.otus.spring.hw03.services.students.GreetingService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Given greeting service")
class GreetingServiceImplTest {

    @MockBean
    private I18nService i18n;

    @MockBean
    private InputOutputService io;

    @Autowired
    private GreetingService greetingService;

    @Captor
    private ArgumentCaptor<?> argsCaptor;

    @Test
    @DisplayName("when getting student with amount of questions, then write student full name and amount of questions")
    void when_GettingStudentWithAmountOfQuestions_then_WriteStudentFullNameAndAmountOfQuestions() {
        // when
        int numberOfQuestions = 5;
        Student student = getStudent();
        greetingService.greet(student, numberOfQuestions);
        verify(i18n, atLeastOnce()).getMessage(anyString(), argsCaptor.capture());

        // then
        assertThat(argsCaptor.getAllValues())
                .map(String::valueOf)
                .anyMatch(value -> value.equalsIgnoreCase(student.getFullName()))
                .anyMatch(value -> value.equalsIgnoreCase(String.valueOf(numberOfQuestions)));
    }

    Student getStudent() {
        return new Student("Test", "Student");
    }
}

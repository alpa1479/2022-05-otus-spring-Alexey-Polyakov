package ru.otus.spring.hw02.services.students.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.services.students.StudentInputValidationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Given student input validation service")
class StudentInputValidationServiceImplTest {

    private StudentInputValidationService studentInputValidationService;

    @BeforeEach
    void setup() {
        studentInputValidationService = new StudentInputValidationServiceImpl();
    }

    @Test
    @DisplayName("when user typed his name in correct format, then return true")
    void when_UserTypedHisNameInIncorrectFormat_then_ReturnTrue() {
        assertThat(studentInputValidationService.validateStudentName("Test")).isTrue();
    }

    @Test
    @DisplayName("when user typed his name in incorrect format, then return false")
    void when_UserTypedHisNameInIncorrectFormat_then_ReturnFalse() {
        assertThat(studentInputValidationService.validateStudentName("incorrectname12345")).isFalse();
    }

    @Test
    @DisplayName("when user typed number of answer in correct format and in range of available answers, then return true")
    void when_UserTypedNumberOfAnswerInCorrectFormatAndInRangeOfAvailableAnswers_then_ReturnTrue() {
        assertThat(studentInputValidationService.validateSelectedAnswer("1", 3)).isTrue();
    }

    @Test
    @DisplayName("when user typed number of answer in correct format but out of range of available answers, then return false")
    void when_UserTypedNumberOfAnswerInCorrectFormatButOutOfRangeOfAvailableAnswers_then_ReturnFalse() {
        assertThat(studentInputValidationService.validateSelectedAnswer("3", 2)).isFalse();
    }

    @Test
    @DisplayName("when user typed number of answer in incorrect format, then return false")
    void when_UserTypedNumberOfAnswerInIncorrectFormat_then_ReturnFalse() {
        assertThat(studentInputValidationService.validateSelectedAnswer("t", 3)).isFalse();
    }

    @Test
    @DisplayName("when user typed continue command as y, then return true")
    void when_UserTypedContinueCommandAsY_then_ReturnTrue() {
        assertThat(studentInputValidationService.validateContinueCommand("y")).isTrue();
    }

    @Test
    @DisplayName("when user typed continue command as n, then return true")
    void when_UserTypedContinueCommandAsN_then_ReturnTrue() {
        assertThat(studentInputValidationService.validateContinueCommand("n")).isTrue();
    }

    @Test
    @DisplayName("when user typed continue command in incorrect format, then return false")
    void when_UserTypedContinueCommandInIncorrectFormat_then_ReturnFalse() {
        assertThat(studentInputValidationService.validateContinueCommand("some command")).isFalse();
    }
}

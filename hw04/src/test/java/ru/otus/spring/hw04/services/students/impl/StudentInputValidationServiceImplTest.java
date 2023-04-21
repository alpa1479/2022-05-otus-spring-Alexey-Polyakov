package ru.otus.spring.hw04.services.students.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw04.services.i18n.I18nService;
import ru.otus.spring.hw04.services.students.StudentInputValidationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Given student input validation service")
class StudentInputValidationServiceImplTest {

    @MockBean
    private I18nService i18n;

    @Autowired
    private StudentInputValidationService studentInputValidationService;

    @Test
    @DisplayName("when user typed his name in correct format, then return true")
    void when_UserTypedHisNameInIncorrectFormat_then_ReturnTrue() {
        when(i18n.getMessage(anyString())).thenReturn("Test");
        assertThat(studentInputValidationService.validateStudentName("Test")).isTrue();
    }

    @Test
    @DisplayName("when user typed his name in incorrect format, then return false")
    void when_UserTypedHisNameInIncorrectFormat_then_ReturnFalse() {
        when(i18n.getMessage(anyString())).then(returnsFirstArg());
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
}

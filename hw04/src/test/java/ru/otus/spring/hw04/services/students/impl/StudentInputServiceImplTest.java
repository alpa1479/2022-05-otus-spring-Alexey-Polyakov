package ru.otus.spring.hw04.services.students.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw04.services.io.InputOutputService;
import ru.otus.spring.hw04.services.questions.QuestionsWriter;
import ru.otus.spring.hw04.services.students.StudentInputService;
import ru.otus.spring.hw04.services.students.StudentInputValidationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Given student input service")
class StudentInputServiceImplTest {

    @MockBean
    private InputOutputService io;

    @MockBean
    private QuestionsWriter questionsWriter;

    @MockBean
    private StudentInputValidationService studentInputValidationService;

    @Autowired
    private StudentInputService studentInputService;

    @Test
    @DisplayName("when user typed his name, then return name as string")
    void when_UserTypedHisName_then_ReturnNameAsString() {
        // given
        given(io.readLine()).willReturn("Test");
        given(studentInputValidationService.validateStudentName(anyString())).willReturn(true);

        // when
        String actualName = studentInputService.askName("First Name");

        // then
        String expectedName = "Test";
        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("when user typed his name in incorrect format, then ask to provide name again")
    void when_UserTypedHisNameInIncorrectFormat_then_AskToProvideNameAgain() {
        // given
        given(io.readLine()).willReturn("Test");
        given(studentInputValidationService.validateStudentName(anyString())).willReturn(false).willReturn(true);

        // when
        studentInputService.askName("First Name");

        // then
        verify(io, times(2)).readLine();
    }
}

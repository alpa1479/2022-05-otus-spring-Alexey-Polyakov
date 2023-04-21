package ru.otus.spring.hw03.services.survey.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.io.InputOutputService;
import ru.otus.spring.hw03.services.survey.SurveyResultWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Given survey result writer")
class SurveyResultWriterImplTest {

    @MockBean
    private I18nService i18n;

    @MockBean
    private InputOutputService io;

    @Autowired
    private SurveyResultWriter surveyResultWriter;

    @Captor
    private ArgumentCaptor<?> argsCaptor;

    @Test
    @DisplayName("when getting survey result to write, then content should have number of questions and student full name")
    void when_GettingSurveyResultToWrite_then_ContentShouldHaveNumberOfQuestionsAndStudentFullName() {
        // when
        SurveyResult surveyResult = getSurveyResult();
        surveyResultWriter.write(surveyResult);
        verify(i18n, atLeastOnce()).getMessage(anyString(), argsCaptor.capture());

        // then
        assertThat(argsCaptor.getAllValues())
                .map(String::valueOf)
                .anyMatch(value -> value.contains(surveyResult.getStudent().getFullName()))
                .anyMatch(value -> value.contains(String.valueOf(surveyResult.getCorrectAnswers())));
    }

    SurveyResult getSurveyResult() {
        return new SurveyResult(new Student("Test", "Student"), true, 4);
    }
}

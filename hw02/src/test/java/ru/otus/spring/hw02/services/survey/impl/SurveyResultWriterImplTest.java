package ru.otus.spring.hw02.services.survey.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.io.InputOutputService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@MockitoSettings
@DisplayName("Given survey result writer")
class SurveyResultWriterImplTest {

    @Mock
    private InputOutputService io;

    @InjectMocks
    private SurveyResultWriterImpl surveyResultWriter;

    @Test
    @DisplayName("when getting survey result to write, then content should have number of questions and student full name")
    void when_GettingSurveyResultToWrite_then_ContentShouldHaveNumberOfQuestionsAndStudentFullName() {
        // when
        SurveyResult surveyResult = getSurveyResult();
        surveyResultWriter.write(surveyResult);
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(io, atLeastOnce()).write(stringCaptor.capture());

        // then
        assertThat(stringCaptor.getAllValues())
                .anyMatch(value -> value.contains(surveyResult.getStudent().getFullName()))
                .anyMatch(value -> value.contains(String.valueOf(surveyResult.getCorrectAnswers())));
    }

    SurveyResult getSurveyResult() {
        return new SurveyResult(new Student("Test", "Student"), true, 4);
    }
}

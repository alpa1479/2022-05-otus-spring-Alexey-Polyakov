package ru.otus.spring.hw02.services.answers.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.otus.spring.hw02.argumentproviders.SurveyResultArgumentProvider;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.answers.AnswersChecker;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Given answers checker")
class AnswersCheckerImplTest {

    private AnswersChecker answersChecker;

    @BeforeEach
    void setup() {
        answersChecker = new AnswersCheckerImpl(4);
    }

    @DisplayName("when student has provided his answers, then count answers and return survey result")
    @ParameterizedTest(name = "{index} - for {2}")
    @ArgumentsSource(SurveyResultArgumentProvider.class)
    void when_StudentHasIncorrectAmountOfAnswers_then_ReturnFailedSurveyResult(Student student, List<Answer> answers, SurveyResult expectedSurveyResult) {
        // when
        SurveyResult actualSurveyResult = answersChecker.check(student, answers);

        // then
        assertThat(actualSurveyResult).isEqualTo(expectedSurveyResult);
    }
}

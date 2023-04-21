package ru.otus.spring.hw02.services.questions.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.exception.IncorrectQuestionFormatException;
import ru.otus.spring.hw02.services.questions.QuestionsParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.*;

@DisplayName("Given csv questions parser")
class CsvQuestionsParserTest {

    private QuestionsParser questionsParser;

    @BeforeEach
    void setup() {
        questionsParser = new CsvQuestionsParser("-", ",", ";");
    }

    @Test
    @DisplayName("when getting correctly formatted lines, then parse lines to question objects")
    void when_GettingCorrectlyFormattedLines_then_ParseLinesToQuestionObjects() {
        // when
        List<Question> questions = questionsParser.parse(getCorrectlyFormattedQuestionLines());

        // then
        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }

    @Test
    @DisplayName("when getting line with missed mandatory field, then throw IncorrectQuestionFormatException")
    void when_GettingLineWithMissedMandatoryField_then_ThrowIncorrectQuestionFormatException() {
        // when
        Throwable throwable = Assertions.catchThrowable(() -> questionsParser.parse(getQuestionLinesWithoutMandatoryField()));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IncorrectQuestionFormatException.class);
    }


    @Test
    @DisplayName("when getting line with incorrect marked answer value, then throw IncorrectQuestionFormatException")
    void when_GettingLineWithIncorrectMarkedAnswer_then_ThrowIncorrectQuestionFormatException() {
        // when
        Throwable throwable = Assertions.catchThrowable(() -> questionsParser.parse(getQuestionLinesWithIncorrectOrderValue()));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IncorrectQuestionFormatException.class);
    }

    @Test
    @DisplayName("when getting line with incorrect order value, then throw IncorrectQuestionFormatException")
    void when_GettingLineWithIncorrectOrderValue_then_ThrowIncorrectQuestionFormatException() {
        // when
        Throwable throwable = Assertions.catchThrowable(() -> questionsParser.parse(getQuestionLinesWithIncorrectMarkedAnswer()));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IncorrectQuestionFormatException.class);
    }
}

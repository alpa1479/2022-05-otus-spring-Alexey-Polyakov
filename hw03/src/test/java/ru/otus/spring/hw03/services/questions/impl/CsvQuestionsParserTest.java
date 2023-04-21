package ru.otus.spring.hw03.services.questions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw03.domain.Question;
import ru.otus.spring.hw03.exception.IncorrectQuestionFormatException;
import ru.otus.spring.hw03.services.questions.QuestionsParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static ru.otus.spring.hw03.util.QuestionsDataGenerator.*;

@SpringBootTest
@DisplayName("Given csv questions parser")
class CsvQuestionsParserTest {

    @Autowired
    private QuestionsParser questionsParser;

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
        Throwable throwable = catchThrowable(() -> questionsParser.parse(getQuestionLinesWithoutMandatoryField()));

        // then
        assertThat(throwable).isInstanceOf(IncorrectQuestionFormatException.class);
    }


    @Test
    @DisplayName("when getting line with incorrect marked answer value, then throw IncorrectQuestionFormatException")
    void when_GettingLineWithIncorrectMarkedAnswer_then_ThrowIncorrectQuestionFormatException() {
        // when
        Throwable throwable = catchThrowable(() -> questionsParser.parse(getQuestionLinesWithIncorrectOrderValue()));

        // then
        assertThat(throwable).isInstanceOf(IncorrectQuestionFormatException.class);
    }

    @Test
    @DisplayName("when getting line with incorrect order value, then throw IncorrectQuestionFormatException")
    void when_GettingLineWithIncorrectOrderValue_then_ThrowIncorrectQuestionFormatException() {
        // when
        Throwable throwable = catchThrowable(() -> questionsParser.parse(getQuestionLinesWithIncorrectMarkedAnswer()));

        // then
        assertThat(throwable).isInstanceOf(IncorrectQuestionFormatException.class);
    }
}

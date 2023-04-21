package ru.otus.spring.hw02.services.questions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.QuestionsParser;
import ru.otus.spring.hw02.services.questions.QuestionsReader;
import ru.otus.spring.hw02.services.questions.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestions;

@MockitoSettings
@DisplayName("Given csv file questions reader")
class CsvFileQuestionsReaderTest {

    @Mock
    private ResourceFileReader resourceFileReader;

    @Mock
    private QuestionsParser questionsParser;

    private QuestionsReader questionsReader;

    @BeforeEach
    void setup() {
        questionsReader = new CsvFileQuestionsReader("/questions.csv", questionsParser, resourceFileReader);
    }

    @Test
    @DisplayName("when csv file is present, then read and return all questions as objects")
    void when_CsvFileIsPresent_then_ReadAndReturnAllQuestionsAsObjects() {
        // given
        given(questionsParser.parse(anyList())).willReturn(getQuestions());

        // when
        List<Question> questions = questionsReader.read();

        // then
        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }
}

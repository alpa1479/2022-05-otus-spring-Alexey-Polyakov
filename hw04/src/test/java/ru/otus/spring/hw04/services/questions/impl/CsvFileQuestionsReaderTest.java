package ru.otus.spring.hw04.services.questions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw04.domain.Question;
import ru.otus.spring.hw04.services.questions.QuestionsParser;
import ru.otus.spring.hw04.services.questions.QuestionsReader;
import ru.otus.spring.hw04.services.questions.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static ru.otus.spring.hw04.util.QuestionsDataGenerator.getQuestions;

@SpringBootTest
@DisplayName("Given csv file questions reader")
class CsvFileQuestionsReaderTest {

    @MockBean
    private ResourceFileReader resourceFileReader;

    @MockBean
    private QuestionsParser questionsParser;

    @Autowired
    private QuestionsReader questionsReader;

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

package ru.otus.spring.hw04.services.questions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw04.domain.Question;
import ru.otus.spring.hw04.services.io.InputOutputService;
import ru.otus.spring.hw04.services.questions.QuestionsWriter;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.hw04.util.QuestionsDataGenerator.getQuestion;

@SpringBootTest
@DisplayName("Given console questions writer")
class ConsoleQuestionsWriterTest {

    @MockBean
    private InputOutputService io;

    @Autowired
    private QuestionsWriter questionsWriter;

    @Test
    @DisplayName("when question object passed to write, then write question value")
    void when_QuestionObjectPassedToWrite_then_WriteQuestionValue() {
        // when
        Question question = getQuestion();
        questionsWriter.write(question);

        // then
        verify(io).write(contains(question.getValue()));
    }
}

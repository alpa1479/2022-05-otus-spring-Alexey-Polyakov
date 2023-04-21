package ru.otus.spring.hw01.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.services.QuestionsWriter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static ru.otus.spring.hw01.asserts.ConsoleOutputAssert.assertThat;

@DisplayName("Console writer")
class ConsoleWriterTest {

    @Test
    @DisplayName("should correctly write questions and answers to the output stream")
    void shouldWriteQuestionsWithAnswersInCorrectFormat() {
        // given
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        QuestionsWriter consoleWriter = new ConsoleWriter(new PrintStream(out));

        // when
        List<Question> questions = getQuestions();
        consoleWriter.write(questions);

        // then
        String expectedOutput = """
                Java survey, please choose one possible answer from the questions:
                  1. Is it possible to have multiple inheritance in Java?
                  	- no
                  	- yes
                """;
        assertThat(out).isOutputWithoutSeparatorsEqualTo(expectedOutput);
    }

    private List<Question> getQuestions() {
        return List.of(
                new Question("Is it possible to have multiple inheritance in Java?", List.of("no", "yes"))
        );
    }
}

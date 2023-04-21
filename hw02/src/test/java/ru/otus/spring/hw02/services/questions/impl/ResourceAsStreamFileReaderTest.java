package ru.otus.spring.hw02.services.questions.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.exception.ResourceFileNotFoundException;
import ru.otus.spring.hw02.services.questions.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getCorrectlyFormattedQuestionLines;

@DisplayName("Given resource file reader")
class ResourceAsStreamFileReaderTest {

    private ResourceFileReader resourceFileReader;

    @BeforeEach
    void setup() {
        resourceFileReader = new ResourceAsStreamFileReader();
    }

    @Test
    @DisplayName("when resource file is present, then read content of file to list of strings")
    void when_ResourceFileIsPresent_then_ReadContentOfFileToListOfStrings() {
        // when
        List<String> lines = resourceFileReader.readAllLines("/questions.csv");

        // then
        List<String> expectedLines = getCorrectlyFormattedQuestionLines();
        assertThat(lines).isEqualTo(expectedLines);
    }

    @Test
    @DisplayName("when resource file is not present, then throw ResourceFileNotFoundException")
    void when_ResourceFileIsNotPresent_then_ThrowResourceFileNotFoundException() {
        // when
        Throwable throwable = Assertions.catchThrowable(() -> resourceFileReader.readAllLines("not-exist-file"));

        // then
        Assertions.assertThat(throwable).isInstanceOf(ResourceFileNotFoundException.class);
    }
}

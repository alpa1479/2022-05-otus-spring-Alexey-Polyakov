package ru.otus.spring.hw01.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw01.services.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Resource file reader")
class ResourceAsStreamFileReaderTest {

    @Test
    @DisplayName("should correctly read questions from csv file")
    void shouldCorrectlyReadQuestionsFromCsvFile() {
        // given
        ResourceFileReader resourceFileReader = new ResourceAsStreamFileReader();

        // when
        List<String> lines = resourceFileReader.readAllLines("/questions.csv");

        // then
        List<String> expectedLines = getQuestionLines();
        assertThat(lines).isEqualTo(expectedLines);
    }

    private List<String> getQuestionLines() {
        return List.of(
                "question, answers",
                "How many OOP principles exist?,one;three;five;seven;nine",
                "Is it possible to have multiple inheritance in Java?,no;yes",
                "What design pattern name is correct?,Designer;Element;Adapter",
                "How many primitive types java has?,five;four;eight;seven",
                "Which class do all classes in Java extend?,Proxy;Object;Class;Entity"
        );
    }
}

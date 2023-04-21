package ru.otus.spring.hw01.asserts;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.io.ByteArrayOutputStream;

public class ConsoleOutputAssert extends AbstractAssert<ConsoleOutputAssert, ByteArrayOutputStream> {

    private ConsoleOutputAssert(ByteArrayOutputStream outputStream) {
        super(outputStream, ConsoleOutputAssert.class);
    }

    public static ConsoleOutputAssert assertThat(ByteArrayOutputStream outputStream) {
        return new ConsoleOutputAssert(outputStream);
    }

    public ConsoleOutputAssert isOutputWithoutSeparatorsEqualTo(String expectedOutput) {
        Assertions.assertThat(removeSeparators(actual.toString())).isEqualTo(removeSeparators(expectedOutput));
        return this;
    }

    private String removeSeparators(String string) {
        return string
                .replaceAll("\s", "")
                .replaceAll("\n", "")
                .replaceAll("\r", "");
    }
}

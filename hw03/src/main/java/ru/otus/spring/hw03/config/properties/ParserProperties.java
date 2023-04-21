package ru.otus.spring.hw03.config.properties;

import lombok.Data;

@Data
public class ParserProperties {

    private final String answersSeparator;
    private final String questionsSeparator;
    private final String correctMarkerSeparator;
}

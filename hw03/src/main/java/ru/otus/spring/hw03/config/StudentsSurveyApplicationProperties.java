package ru.otus.spring.hw03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import ru.otus.spring.hw03.config.properties.AnswerProperties;
import ru.otus.spring.hw03.config.properties.ParserProperties;
import ru.otus.spring.hw03.config.properties.QuestionProperties;

@Data
@ConstructorBinding
@ConfigurationProperties("survey")
public class StudentsSurveyApplicationProperties {

    private final String locale;
    private final ParserProperties parser;
    private final AnswerProperties answers;
    private final QuestionProperties questions;
}

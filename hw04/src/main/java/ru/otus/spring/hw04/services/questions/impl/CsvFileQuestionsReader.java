package ru.otus.spring.hw04.services.questions.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.config.StudentsSurveyApplicationProperties;
import ru.otus.spring.hw04.config.properties.QuestionProperties;
import ru.otus.spring.hw04.domain.Question;
import ru.otus.spring.hw04.services.questions.QuestionsParser;
import ru.otus.spring.hw04.services.questions.QuestionsReader;
import ru.otus.spring.hw04.services.questions.ResourceFileReader;

import java.util.List;

@Service
public class CsvFileQuestionsReader implements QuestionsReader {

    private final QuestionsParser questionsParser;
    private final ResourceFileReader resourceFileReader;
    private final QuestionProperties questionProperties;

    public CsvFileQuestionsReader(QuestionsParser questionsParser,
                                  ResourceFileReader resourceFileReader,
                                  StudentsSurveyApplicationProperties properties) {
        this.questionProperties = properties.getQuestions();
        this.questionsParser = questionsParser;
        this.resourceFileReader = resourceFileReader;
    }

    @Override
    public List<Question> read() {
        List<String> lines = resourceFileReader.readAllLines(questionProperties.getFilepath());
        return questionsParser.parse(lines);
    }
}

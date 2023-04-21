package ru.otus.spring.hw02.services.questions.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.QuestionsParser;
import ru.otus.spring.hw02.services.questions.QuestionsReader;
import ru.otus.spring.hw02.services.questions.ResourceFileReader;

import java.util.List;

@Service
public class CsvFileQuestionsReader implements QuestionsReader {

    private final String filepath;
    private final QuestionsParser questionsParser;
    private final ResourceFileReader resourceFileReader;

    public CsvFileQuestionsReader(@Value("${questions.filepath}") String filepath,
                                  QuestionsParser questionsParser,
                                  ResourceFileReader resourceFileReader) {
        this.filepath = filepath;
        this.questionsParser = questionsParser;
        this.resourceFileReader = resourceFileReader;
    }

    @Override
    public List<Question> read() {
        List<String> lines = resourceFileReader.readAllLines(filepath);
        return questionsParser.parse(lines);
    }
}

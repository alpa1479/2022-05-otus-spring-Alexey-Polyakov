package ru.otus.spring.hw01.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import ru.otus.spring.hw01.services.QuestionsReader;
import ru.otus.spring.hw01.services.QuestionsWriter;

@RequiredArgsConstructor
public class StudentsSurveyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final QuestionsReader questionsReader;
    private final QuestionsWriter questionsWriter;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        questionsWriter.write(questionsReader.read());
    }
}

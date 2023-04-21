package ru.otus.spring.hw02.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.application.StudentsSurveyApplication;

@Service
@RequiredArgsConstructor
public class StudentsSurveyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final StudentsSurveyApplication studentsSurveyApplication;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        studentsSurveyApplication.run();
    }
}

package ru.otus.spring.hw03.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.application.StudentsSurveyApplication;

@Service
@RequiredArgsConstructor
public class StudentsSurveyApplicationRunner implements CommandLineRunner {

    private final StudentsSurveyApplication studentsSurveyApplication;

    @Override
    public void run(String... args) {
        studentsSurveyApplication.run();
    }
}

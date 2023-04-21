package ru.otus.spring.hw03.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.io.InputOutputService;
import ru.otus.spring.hw03.services.students.GreetingService;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final I18nService i18n;
    private final InputOutputService io;

    @Override
    public void greet() {
        io.write(String.format("%s%n", i18n.getMessage("output.greeting.anonymous")));
    }

    @Override
    public void greet(Student student, int numberOfQuestions) {
        io.write(String.format("%s%n", i18n.getMessage("output.greeting.identified", student.getFullName(), numberOfQuestions)));
    }
}

package ru.otus.spring.hw02.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.services.io.InputOutputService;
import ru.otus.spring.hw02.services.students.GreetingService;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final InputOutputService io;

    @Override
    public void greet() {
        io.write("Welcome to the Java Survey! Please provide the following details to start:\n");
    }

    @Override
    public void greet(Student student, int numberOfQuestions) {
        io.write(format("%nHello %s! We prepared %d questions for you. Please choose one possible answer from the existing ones. Let's start!%n",
                student.getFullName(), numberOfQuestions));
    }
}

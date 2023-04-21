package ru.otus.spring.hw02.services.students;

import ru.otus.spring.hw02.domain.Student;

public interface GreetingService {

    void greet();

    void greet(Student student, int numberOfQuestions);
}

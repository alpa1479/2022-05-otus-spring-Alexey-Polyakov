package ru.otus.spring.hw03.services.students;

import ru.otus.spring.hw03.domain.Student;

public interface GreetingService {

    void greet();

    void greet(Student student, int numberOfQuestions);
}

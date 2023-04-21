package ru.otus.spring.hw04.services.students;

import ru.otus.spring.hw04.domain.Student;

public interface GreetingService {

    void greet();

    void greet(Student student, int numberOfQuestions);
}

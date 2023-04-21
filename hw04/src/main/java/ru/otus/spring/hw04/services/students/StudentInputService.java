package ru.otus.spring.hw04.services.students;

import ru.otus.spring.hw04.domain.Answer;
import ru.otus.spring.hw04.domain.Question;

public interface StudentInputService {

    String askName(String type);

    Answer askQuestion(Question question);
}

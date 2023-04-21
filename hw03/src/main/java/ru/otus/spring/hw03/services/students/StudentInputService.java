package ru.otus.spring.hw03.services.students;

import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Question;

public interface StudentInputService {

    String askName(String type);

    Answer askQuestion(Question question);

    boolean askContinue();
}

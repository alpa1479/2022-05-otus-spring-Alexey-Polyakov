package ru.otus.spring.hw02.services.students;

import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;

public interface StudentInputService {

    String askName(String type);

    Answer askQuestion(Question question);

    boolean askContinue();
}

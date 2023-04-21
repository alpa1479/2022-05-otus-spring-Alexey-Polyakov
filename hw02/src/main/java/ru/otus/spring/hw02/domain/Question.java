package ru.otus.spring.hw02.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private final int order;
    private final String value;
    private final List<Answer> answers;

    public Answer getAnswer(int index) {
        return answers.get(index);
    }

    public int getAnswersSize() {
        return answers.size();
    }
}

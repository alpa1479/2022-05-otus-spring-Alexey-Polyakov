package ru.otus.spring.hw03.util;

import lombok.experimental.UtilityClass;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Question;

import java.util.List;

@UtilityClass
public class  QuestionsDataGenerator {

    public static Question getQuestion() {
        return new Question(1, "Is it possible to have multiple inheritance in Java?", List.of(
                new Answer("no", true),
                new Answer("yes", false)
        ));
    }

    public static List<String> getCorrectlyFormattedQuestionLines() {
        return List.of(
                "order, question, answers",
                "1,How many OOP principles exist?,one;three@true;five;seven;nine",
                "2,Is it possible to have multiple inheritance in Java?,no@true;yes",
                "3,What design pattern name is correct?,Designer;Element;Adapter@true",
                "4,How many primitive types java has?,five;four;eight@true;seven",
                "5,Which class do all classes in Java extend?,Proxy;Object@true;Class;Entity"
        );
    }

    public static List<String> getQuestionLinesWithoutMandatoryField() {
        return List.of(
                "order, question, answers",
                "1,How many OOP principles exist?",
                "2,Is it possible to have multiple inheritance in Java?,no@true;yes",
                "3,What design pattern name is correct?,Designer;Element;Adapter@true",
                "4,How many primitive types java has?,five;four;eight@true;seven",
                "5,Which class do all classes in Java extend?,Proxy;Object@true;Class;Entity"
        );
    }

    public static List<String> getQuestionLinesWithIncorrectOrderValue() {
        return List.of(
                "order, question, answers",
                "t,How many OOP principles exist?,one;three@true;five;seven;nine",
                "2,Is it possible to have multiple inheritance in Java?,no@true;yes",
                "3,What design pattern name is correct?,Designer;Element;Adapter@true",
                "4,How many primitive types java has?,five;four;eight@true;seven",
                "5,Which class do all classes in Java extend?,Proxy;Object@true;Class;Entity"
        );
    }

    public static List<String> getQuestionLinesWithIncorrectMarkedAnswer() {
        return List.of(
                "order, question, answers",
                "1,How many OOP principles exist?,one;three@true;five;seven;nine",
                "2,Is it possible to have multiple inheritance in Java?,no@test;yes",
                "3,What design pattern name is correct?,Designer;Element;Adapter@true",
                "4,How many primitive types java has?,five;four;eight@true;seven",
                "5,Which class do all classes in Java extend?,Proxy;Object@true;Class;Entity"
        );
    }

    public static List<Question> getQuestions() {
        return List.of(
                new Question(1, "How many OOP principles exist?", List.of(
                        new Answer("one", false),
                        new Answer("three", true),
                        new Answer("five", false),
                        new Answer("seven", false),
                        new Answer("nine", false)
                )),
                new Question(2, "Is it possible to have multiple inheritance in Java?", List.of(
                        new Answer("no", true),
                        new Answer("yes", false)
                )),
                new Question(3, "What design pattern name is correct?", List.of(
                        new Answer("Designer", false),
                        new Answer("Element", false),
                        new Answer("Adapter", true)
                )),
                new Question(4, "How many primitive types java has?", List.of(
                        new Answer("five", false),
                        new Answer("four", false),
                        new Answer("eight", true),
                        new Answer("seven", false)
                )),
                new Question(5, "Which class do all classes in Java extend?", List.of(
                        new Answer("Proxy", false),
                        new Answer("Object", true),
                        new Answer("Class", false),
                        new Answer("Entity", false)
                ))
        );
    }
}

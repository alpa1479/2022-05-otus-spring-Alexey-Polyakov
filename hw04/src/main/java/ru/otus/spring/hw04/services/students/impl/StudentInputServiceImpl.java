package ru.otus.spring.hw04.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.domain.Answer;
import ru.otus.spring.hw04.domain.Question;
import ru.otus.spring.hw04.services.i18n.I18nService;
import ru.otus.spring.hw04.services.io.InputOutputService;
import ru.otus.spring.hw04.services.questions.QuestionsWriter;
import ru.otus.spring.hw04.services.students.StudentInputService;
import ru.otus.spring.hw04.services.students.StudentInputValidationService;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StudentInputServiceImpl implements StudentInputService {

    private static final String INPUT_FORMAT = "%s - ";

    private final I18nService i18n;
    private final InputOutputService io;
    private final QuestionsWriter questionsWriter;
    private final StudentInputValidationService studentInputValidationService;

    @Override
    public String askName(String type) {
        io.write(format(INPUT_FORMAT, type));
        String name = io.readLine();
        while (!studentInputValidationService.validateStudentName(name)) {
            io.write(format("%s%n", i18n.getMessage("output.student.input.incorrect-format", name)));
            io.write(format(INPUT_FORMAT, type));
            name = io.readLine();
        }
        return name;
    }

    @Override
    public Answer askQuestion(Question question) {
        questionsWriter.write(question);
        io.write(format(INPUT_FORMAT, i18n.getMessage("output.student.input.answer")));
        String indexOfAnswer = io.readLine();
        while (!studentInputValidationService.validateSelectedAnswer(indexOfAnswer, question.getAnswersSize())) {
            io.write(format("%s%n", i18n.getMessage("output.student.input.incorrect-format", indexOfAnswer)));
            io.write(format(INPUT_FORMAT, i18n.getMessage("output.student.input.answer")));
            indexOfAnswer = io.readLine();
        }
        return question.getAnswer(Integer.parseInt(indexOfAnswer) - 1);
    }
}

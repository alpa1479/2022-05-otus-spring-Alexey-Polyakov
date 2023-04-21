package ru.otus.spring.hw04.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.services.i18n.I18nService;
import ru.otus.spring.hw04.services.students.StudentInputValidationService;

@Service
@RequiredArgsConstructor
public class StudentInputValidationServiceImpl implements StudentInputValidationService {

    private final I18nService i18n;

    @Override
    public boolean validateStudentName(String name) {
        return StringUtils.isNotEmpty(name) && name.matches(i18n.getMessage("validation.regex.student.input.name"));
    }

    @Override
    public boolean validateSelectedAnswer(String selectedAnswer, int answersSize) {
        if (StringUtils.isNumeric(selectedAnswer)) {
            int selectedAnswerIndex = Integer.parseInt(selectedAnswer);
            return selectedAnswerIndex > 0 && selectedAnswerIndex <= answersSize;
        }
        return false;
    }
}

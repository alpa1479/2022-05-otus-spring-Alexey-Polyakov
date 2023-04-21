package ru.otus.spring.hw03.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.students.StudentIdentificationService;
import ru.otus.spring.hw03.services.students.StudentInputService;

@Service
@RequiredArgsConstructor
public class StudentIdentificationServiceImpl implements StudentIdentificationService {

    private final I18nService i18n;
    private final StudentInputService studentInputService;

    @Override
    public Student identify() {
        String firstname = studentInputService.askName(i18n.getMessage("output.student.identification.firstname"));
        String lastname = studentInputService.askName(i18n.getMessage("output.student.identification.lastname"));
        return new Student(firstname, lastname);
    }
}

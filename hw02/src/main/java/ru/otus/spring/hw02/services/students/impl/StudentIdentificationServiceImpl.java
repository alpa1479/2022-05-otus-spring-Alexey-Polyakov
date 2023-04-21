package ru.otus.spring.hw02.services.students.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.services.students.StudentIdentificationService;
import ru.otus.spring.hw02.services.students.StudentInputService;

@Service
@RequiredArgsConstructor
public class StudentIdentificationServiceImpl implements StudentIdentificationService {

    private final StudentInputService studentInputService;

    @Override
    public Student identify() {
        String firstname = studentInputService.askName("First name");
        String lastname = studentInputService.askName("Last name");
        return new Student(firstname, lastname);
    }
}

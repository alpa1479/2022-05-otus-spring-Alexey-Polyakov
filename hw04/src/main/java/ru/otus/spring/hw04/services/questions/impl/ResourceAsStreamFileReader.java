package ru.otus.spring.hw04.services.questions.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw04.exception.ReadResourceFileException;
import ru.otus.spring.hw04.exception.ResourceFileNotFoundException;
import ru.otus.spring.hw04.services.questions.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static ru.otus.spring.hw04.util.Validations.requireNonNull;

@Service
public class ResourceAsStreamFileReader implements ResourceFileReader {

    @Override
    public List<String> readAllLines(String filepath) {
        try (InputStream inputStream = getResourceInputStream(filepath);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new ReadResourceFileException(String.format("Unable to read resource file by path [%s]", filepath));
        }
    }

    private InputStream getResourceInputStream(String filepath) {
        InputStream inputStream = getClass().getResourceAsStream(filepath);
        requireNonNull(inputStream,
                () -> new ResourceFileNotFoundException(String.format("Can't find resource file by path %s", filepath)));
        return inputStream;
    }
}

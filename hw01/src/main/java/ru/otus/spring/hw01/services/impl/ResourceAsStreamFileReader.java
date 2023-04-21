package ru.otus.spring.hw01.services.impl;

import org.apache.commons.lang3.StringUtils;
import ru.otus.spring.hw01.exception.ReadResourceFileException;
import ru.otus.spring.hw01.exception.ResourceFileNotFoundException;
import ru.otus.spring.hw01.services.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static ru.otus.spring.hw01.util.Validations.requireNonNull;

public class ResourceAsStreamFileReader implements ResourceFileReader {

    @Override
    public List<String> readAllLines(String filepath) {
        try (InputStream inputStream = getResourceInputStream(filepath);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            return bufferedReader.lines()
                    .filter(StringUtils::isNotEmpty)
                    .toList();

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

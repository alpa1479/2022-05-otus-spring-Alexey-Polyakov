package ru.otus.spring.hw01.services;

import java.util.List;

public interface ResourceFileReader {

    List<String> readAllLines(String filepath);
}

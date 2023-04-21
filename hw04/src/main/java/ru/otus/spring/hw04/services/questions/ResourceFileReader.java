package ru.otus.spring.hw04.services.questions;

import java.util.List;

public interface ResourceFileReader {

    List<String> readAllLines(String filepath);
}

package ru.otus.spring.hw02.services.questions;

import java.util.List;

public interface ResourceFileReader {

    List<String> readAllLines(String filepath);
}

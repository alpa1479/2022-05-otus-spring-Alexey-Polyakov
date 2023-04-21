package ru.otus.spring.hw03.services.io;

public interface InputOutputService {

    String readLine();

    <T> void write(T output);
}

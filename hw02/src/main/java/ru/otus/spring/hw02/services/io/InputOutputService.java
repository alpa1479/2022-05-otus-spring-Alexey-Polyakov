package ru.otus.spring.hw02.services.io;

public interface InputOutputService {

    String readLine();

    <T> void write(T output);
}

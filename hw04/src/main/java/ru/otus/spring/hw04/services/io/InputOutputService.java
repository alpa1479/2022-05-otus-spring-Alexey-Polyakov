package ru.otus.spring.hw04.services.io;

public interface InputOutputService {

    String readLine();

    <T> void write(T output);
}

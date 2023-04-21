package ru.otus.spring.hw03.services.io.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.services.io.InputOutputService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleInputOutputService implements InputOutputService {

    private final Scanner in;
    private final PrintStream out;

    public ConsoleInputOutputService(@Value("#{T(System).in}") InputStream in,
                                     @Value("#{T(System).out}") PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    @Override
    public String readLine() {
        return in.nextLine();
    }

    @Override
    public <T> void write(T output) {
        out.print(output);
    }
}

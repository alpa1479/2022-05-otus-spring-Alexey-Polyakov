package ru.otus.spring.hw15.utils;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Randoms {

    public static final Range DEFAULT_SHORT_RANGE = new Range(1, 2);
    public static final Range DEFAULT_LONG_RANGE = new Range(1, 5);

    @RequiredArgsConstructor
    public static class Range {
        private final int from;
        private final int to;
    }

    public static int randomInt(Range range) {
        return new Random().nextInt(range.to) + range.from;
    }
}

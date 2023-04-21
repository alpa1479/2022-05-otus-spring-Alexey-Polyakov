package ru.otus.spring.hw01.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class Validations {

    public static <T> void requireNonNull(T object, Supplier<? extends RuntimeException> exception) {
        test(object, Objects::isNull, exception);
    }

    public static <T> void requireSize(T[] object, int size, Supplier<? extends RuntimeException> exception) {
        test(object, array -> array.length != size, exception);
    }

    private static <T> void test(T data, Predicate<T> predicate, Supplier<? extends RuntimeException> exception) {
        if (predicate.test(data)) {
            throw exception.get();
        }
    }
}

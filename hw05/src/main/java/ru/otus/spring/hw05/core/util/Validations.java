package ru.otus.spring.hw05.core.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class Validations {

    public static <T> T requireNonNull(T object, Supplier<? extends RuntimeException> exception) {
        test(object, Objects::isNull, exception);
        return object;
    }

    public static int requireValue(int actualNumber, int exceptedNumber, Supplier<? extends RuntimeException> exception) {
        test(actualNumber, number -> number != exceptedNumber, exception);
        return actualNumber;
    }

    public static <T> Collection<T> requireNonEmpty(Collection<T> collection, Supplier<? extends RuntimeException> exception) {
        test(collection, CollectionUtils::isEmpty, exception);
        return collection;
    }

    private static <T> void test(T data, Predicate<T> predicate, Supplier<? extends RuntimeException> exception) {
        if (predicate.test(data)) {
            throw exception.get();
        }
    }
}

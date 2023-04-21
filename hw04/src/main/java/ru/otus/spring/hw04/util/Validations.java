package ru.otus.spring.hw04.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
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

    public static <T> void requireNonEmpty(Collection<T> collection, Supplier<? extends RuntimeException> exception) {
        test(collection, CollectionUtils::isEmpty, exception);
    }

    public static void requireBoolean(String line, Supplier<? extends RuntimeException> exception) {
        test(line, string -> StringUtils.isNotEmpty(string)
                && !(string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false")), exception);
    }

    private static <T> void test(T data, Predicate<T> predicate, Supplier<? extends RuntimeException> exception) {
        if (predicate.test(data)) {
            throw exception.get();
        }
    }
}

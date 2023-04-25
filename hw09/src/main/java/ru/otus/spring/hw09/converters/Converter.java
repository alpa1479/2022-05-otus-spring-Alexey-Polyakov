package ru.otus.spring.hw09.converters;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Converter<I, O> {

    Optional<O> convert(I object);

    default List<O> convert(List<? extends I> objects) {
        return objects.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .flatMap(Optional::stream)
                .filter(Objects::nonNull)
                .toList();
    }
}

package ru.otus.spring.hw08.shell.responsemappers;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public interface ToStringResponseMapper<T> {

    String EMPTY_MESSAGE = "There is no any records";

    String map(T object);

    default String map(Collection<T> collection) {
        return map(collection, System.lineSeparator());
    }

    default String map(Collection<T> collection, String separator) {
        if (CollectionUtils.isEmpty(collection)) {
            return EMPTY_MESSAGE;
        }
        collection = collection.stream().filter(Objects::nonNull).toList();
        if (CollectionUtils.isEmpty(collection)) {
            return EMPTY_MESSAGE;
        }
        return collection.stream()
                .map(this::map)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(separator));
    }
}

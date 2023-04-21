package ru.otus.spring.hw07.shell.responsemappers;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public interface ToStringResponseMapper<T> {

    String map(T object);

    default String map(Collection<T> collection) {
        return map(collection, System.lineSeparator());
    }

    default String map(Collection<T> collection, String separator) {
        if (CollectionUtils.isEmpty(collection)) {
            return "There is no any records";
        }
        return collection.stream()
                .map(this::map)
                .collect(Collectors.joining(separator));
    }
}

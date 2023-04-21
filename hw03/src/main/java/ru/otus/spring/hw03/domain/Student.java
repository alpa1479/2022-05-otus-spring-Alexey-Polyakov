package ru.otus.spring.hw03.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Data
public class Student {

    private final String firstname;
    private final String lastname;

    public String getFullName() {
        return format("%s %s",
                ofNullable(firstname).orElse(StringUtils.EMPTY),
                ofNullable(lastname).orElse(StringUtils.EMPTY)
        );
    }
}

package ru.otus.spring.hw14.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StepType {

    EXTRACT("Extract"),
    TRANSFORM_AND_LOAD("TransformAndLoad");

    private final String name;
}

package ru.otus.spring.hw14.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class EntityForMigration implements Serializable {

    private String newId;
}

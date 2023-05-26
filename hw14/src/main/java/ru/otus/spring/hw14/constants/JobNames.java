package ru.otus.spring.hw14.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobNames {

    LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB("libraryDbFromPostgresToMongoMigrationJob");

    private final String name;
}

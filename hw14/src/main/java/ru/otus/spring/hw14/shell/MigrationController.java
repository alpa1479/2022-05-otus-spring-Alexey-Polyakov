package ru.otus.spring.hw14.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.hw14.constants.JobNames;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@ShellComponent
@RequiredArgsConstructor
public class MigrationController {

    private final JobOperator jobOperator;

    @ShellMethod(value = "Starts LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB migration", key = "run")
    public void run() throws Exception {
        jobOperator.start(JobNames.LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB.getName(), EMPTY);
    }
}

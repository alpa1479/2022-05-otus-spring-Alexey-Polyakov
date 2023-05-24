package ru.otus.spring.hw14.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import ru.otus.spring.hw14.constants.StepType;

import static java.util.Arrays.stream;
import static ru.otus.spring.hw14.constants.JobNames.LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB;
import static ru.otus.spring.hw14.constants.StepType.EXTRACT;
import static ru.otus.spring.hw14.constants.StepType.TRANSFORM_AND_LOAD;

@Configuration
@RequiredArgsConstructor
public class MigrationJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final MigrationJobStepsConfig migrationJobStepsConfig;

    @Bean
    public Job migrateLibraryDbFromPostgresToMongoJob() {
        return jobBuilderFactory.get(LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB.getName())
                .incrementer(new RunIdIncrementer())
                .start(extractFlow())
                .next(transformAndLoadFlow())
                .next(migrationJobStepsConfig.cleanUpStep())
                .end()
                .build();
    }

    private Flow extractFlow() {
        return createFlowFromSteps(
                EXTRACT,
                migrationJobStepsConfig.extractGenresStep(),
                migrationJobStepsConfig.extractAuthorsStep(),
                migrationJobStepsConfig.extractCommentsStep(),
                migrationJobStepsConfig.extractBooksStep()
        );
    }

    private Flow transformAndLoadFlow() {
        return createFlowFromSteps(
                TRANSFORM_AND_LOAD,
                migrationJobStepsConfig.transformAndLoadGenresStep(),
                migrationJobStepsConfig.transformAndLoadAuthorsStep(),
                migrationJobStepsConfig.transformAndLoadBooksStep()
        );
    }

    private Flow createFlowFromSteps(StepType stepType, Step... steps) {
        var flows = stream(steps)
                .map(step -> new FlowBuilder<SimpleFlow>(step.getName()).start(step).build())
                .toList();
        return new FlowBuilder<SimpleFlow>(stepType.getName())
                .split(new SimpleAsyncTaskExecutor())
                .add(flows.toArray(new Flow[0]))
                .build();
    }
}

package ru.otus.spring.hw11.mongock.changeunits;

import com.mongodb.reactivestreams.client.MongoDatabase;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@ChangeUnit(id = "DropDatabaseChangeUnit", order = "1")
public class DropDatabaseChangeUnit {

    private final MongoDatabase database;

    @Execution
    public void dropDatabase() {
        Mono.when(database.drop()).block();
    }

    @RollbackExecution
    public void rollback() {
    }
}

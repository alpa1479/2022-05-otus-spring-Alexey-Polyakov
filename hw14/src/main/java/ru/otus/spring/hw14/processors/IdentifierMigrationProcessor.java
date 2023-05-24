package ru.otus.spring.hw14.processors;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring.hw14.domain.EntityForMigration;

public class IdentifierMigrationProcessor<T extends EntityForMigration> implements ItemProcessor<T, T> {

    @Override
    public T process(T entityForMigration) {
        entityForMigration.setNewId(new ObjectId().toString());
        return entityForMigration;
    }
}

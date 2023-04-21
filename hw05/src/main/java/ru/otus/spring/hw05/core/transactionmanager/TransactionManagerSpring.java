package ru.otus.spring.hw05.core.transactionmanager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw05.core.transactionmanager.actions.RunnableTransactionAction;
import ru.otus.spring.hw05.core.transactionmanager.actions.TransactionAction;

@Component
public class TransactionManagerSpring implements TransactionManager {

    @Override
    @Transactional
    public void doInTransaction(RunnableTransactionAction action) {
        action.run();
    }

    @Override
    @Transactional
    public <T> T doInTransaction(TransactionAction<T> action) {
        return action.get();
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T doInReadOnlyTransaction(TransactionAction<T> action) {
        return action.get();
    }
}

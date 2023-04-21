package ru.otus.spring.hw05.core.transactionmanager;

import ru.otus.spring.hw05.core.transactionmanager.actions.RunnableTransactionAction;
import ru.otus.spring.hw05.core.transactionmanager.actions.TransactionAction;

public interface TransactionManager {

    void doInTransaction(RunnableTransactionAction action);

    <T> T doInTransaction(TransactionAction<T> action);

    <T> T doInReadOnlyTransaction(TransactionAction<T> action);
}

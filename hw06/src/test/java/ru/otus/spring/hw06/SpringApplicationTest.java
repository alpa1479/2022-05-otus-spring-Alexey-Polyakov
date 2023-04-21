package ru.otus.spring.hw06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Given spring application")
@SpringBootTest
class SpringApplicationTest {

    @Test
    @DisplayName("should initialize context of application")
    void shouldLoadContext(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("should enable transactions mechanism")
    void shouldEnableTransactions() {
        assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
    }
}

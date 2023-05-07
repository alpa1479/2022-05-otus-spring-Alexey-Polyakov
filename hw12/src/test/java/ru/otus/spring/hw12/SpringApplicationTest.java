package ru.otus.spring.hw12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Given spring application")
@SpringBootTest
class SpringApplicationTest {

    @Test
    @DisplayName("should initialize context of application")
    void shouldLoadContext(ApplicationContext context) {
        assertThat(context).isNotNull();
    }
}

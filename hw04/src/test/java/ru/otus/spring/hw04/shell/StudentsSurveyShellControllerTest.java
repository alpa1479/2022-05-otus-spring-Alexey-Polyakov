package ru.otus.spring.hw04.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given students survey shell controller")
class StudentsSurveyShellControllerTest {

    @Autowired
    private Shell shell;

    @Test
    @DisplayName("when getting start command before login, then should throw an exception that command not available")
    void when_GettingStartCommandBeforeLogin_then_ShouldThrowAnExceptionThatCommandNotAvailable() {
        assertThat(shell.evaluate(() -> "start")).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

    @Test
    @DisplayName("when getting result command before login, then should throw an exception that command not available")
    void when_GettingResultCommandBeforeLogin_then_ShouldThrowAnExceptionThatCommandNotAvailable() {
        assertThat(shell.evaluate(() -> "result")).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }
}

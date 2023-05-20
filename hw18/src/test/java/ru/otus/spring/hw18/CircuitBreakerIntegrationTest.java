package ru.otus.spring.hw18;

import com.mongodb.MongoSocketClosedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.otus.spring.hw18.dto.AuthorDto;
import ru.otus.spring.hw18.repository.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Given CircuitBreaker")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CircuitBreakerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CircuitBreakerRegistry registry;

    @SpyBean
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("should change state according to configuration")
    void shouldChangeStateAccordingToConfiguration() throws InterruptedException {
        // Check that CircuitBreaker in CLOSED state from the beginning
        CircuitBreaker circuitBreaker = registry.circuitBreaker("mongodb");
        assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);

        // Send successful request
        ResponseEntity<AuthorDto> successfulResponse = restTemplate.getForEntity("/api/v1/authors/{name}", AuthorDto.class, "Thomas Artis");
        assertThat(successfulResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Send error request
        when(authorRepository.findAll()).thenThrow(MongoSocketClosedException.class);
        ResponseEntity<AuthorDto> errorResponse = restTemplate.getForEntity("/api/v1/authors", AuthorDto.class);
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        // Check that CircuitBreaker in OPEN state after we have 50% failure rate - failureRateThreshold: 50
        assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.OPEN);

        // Wait until CircuitBreaker HALF-OPEN state - waitDurationInOpenState: 5s
        Thread.sleep(5000);

        // Send successful request
        successfulResponse = restTemplate.getForEntity("/api/v1/authors/{name}", AuthorDto.class, "Thomas Artis");
        assertThat(successfulResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check that CircuitBreaker in HALF-OPEN state after timeout - waitDurationInOpenState: 5s
        assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.HALF_OPEN);

        // Send successful request
        successfulResponse = restTemplate.getForEntity("/api/v1/authors/{name}", AuthorDto.class, "Thomas Artis");
        assertThat(successfulResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check that CircuitBreaker in CLOSED state after we sent 2 successful request - minimumNumberOfCalls: 2
        assertThat(circuitBreaker.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
    }
}

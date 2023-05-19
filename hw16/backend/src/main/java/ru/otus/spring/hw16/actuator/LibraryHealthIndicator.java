package ru.otus.spring.hw16.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw16.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class LibraryHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        long count = bookRepository.count();
        return count > 0
                ? Health.up().withDetail("booksCount", count).build()
                : Health.down().withDetail("errorMessage", "The library does not contain books").build();
    }
}

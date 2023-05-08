package ru.otus.spring.hw13.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw13.domain.User;
import ru.otus.spring.hw13.domain.UserRole;
import ru.otus.spring.hw13.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Given user repository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("should return all users")
    void shouldReturnAllUsers() {
        assertThat(userRepository.findAll())
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(getExpectedUsers());
    }

    @Test
    @DisplayName("should return user by name")
    void shouldReturnUserByName() {
        assertThat(userRepository.findByName("user1"))
                .get()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(getExpectedUsers().get(0));
    }

    List<User> getExpectedUsers() {
        return List.of(
                new User("user1", "$2a$10$Bj2vYEtYF7GuliGQ5FVOsOeYE0I/eP35L65GAAxx4I685FoaXJ1RG", UserRole.ADMIN),
                new User("user2", "$2a$10$Bj2vYEtYF7GuliGQ5FVOsOeYE0I/eP35L65GAAxx4I685FoaXJ1RG", UserRole.USER),
                new User("user3", "$2a$10$Bj2vYEtYF7GuliGQ5FVOsOeYE0I/eP35L65GAAxx4I685FoaXJ1RG", UserRole.USER)
        );
    }
}
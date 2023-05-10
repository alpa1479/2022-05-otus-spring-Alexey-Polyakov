package ru.otus.spring.hw12.converters.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw12.converters.Converter;
import ru.otus.spring.hw12.domain.User;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public Optional<UserDetails> convert(User user) {
        return ofNullable(user).map(this::map);
    }

    private UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}

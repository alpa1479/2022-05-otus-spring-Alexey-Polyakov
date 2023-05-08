package ru.otus.spring.hw13.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.otus.spring.hw13.domain.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // @formatter:off
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/", "/books", "/books/update").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/books/comment").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET,"/books/create").hasAnyRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/books/create", "/books/update", "/books/delete", "/books/comments/delete").hasAnyRole(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                   .anonymous()
                .and()
                   .formLogin().permitAll()
                .and()
                   .logout().permitAll()
                .and()
                   .exceptionHandling()
                   .accessDeniedPage("/forbidden");
        return http.build();
    }
    // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

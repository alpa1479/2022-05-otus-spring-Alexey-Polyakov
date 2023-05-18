package ru.otus.spring.hw17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw17.dto.AuthorDto;
import ru.otus.spring.hw17.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorsService authorsService;

    @GetMapping("/authors")
    public List<AuthorDto> findAll() {
        return authorsService.findAll();
    }
}
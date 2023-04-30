package ru.otus.spring.hw10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw10.dto.GenreDto;
import ru.otus.spring.hw10.services.GenresService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GenreRestController {

    private final GenresService genresService;

    @GetMapping("/genres")
    public List<GenreDto> findAll() {
        return genresService.findAll();
    }
}
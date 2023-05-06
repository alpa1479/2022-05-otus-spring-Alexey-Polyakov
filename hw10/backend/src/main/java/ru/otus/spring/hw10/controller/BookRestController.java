package ru.otus.spring.hw10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.hw10.dto.BookDto;
import ru.otus.spring.hw10.dto.SaveBookDto;
import ru.otus.spring.hw10.exceptions.NotFoundException;
import ru.otus.spring.hw10.services.BooksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookRestController {

    private final BooksService booksService;

    @GetMapping("/books")
    public List<BookDto> findAll() {
        return booksService.findAll();
    }

    @GetMapping("/books/{id}")
    public SaveBookDto findById(@PathVariable String id) {
        return booksService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/books")
    public ResponseEntity<?> create(@RequestBody SaveBookDto book) {
        booksService.create(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<?> update(@RequestBody SaveBookDto book) {
        booksService.update(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        booksService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
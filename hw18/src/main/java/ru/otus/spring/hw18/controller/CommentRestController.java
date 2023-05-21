package ru.otus.spring.hw18.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.hw18.dto.SaveCommentDto;
import ru.otus.spring.hw18.services.CommentsService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentsService commentsService;

    @PostMapping("/comments")
    public ResponseEntity<?> saveCommentToBook(@RequestBody SaveCommentDto commentDto) {
        commentsService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/books/{id}")
    public ResponseEntity<?> deleteAllByBookId(@PathVariable String id) {
        commentsService.deleteAllByBookId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
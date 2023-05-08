package ru.otus.spring.hw13.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw13.converters.Converter;
import ru.otus.spring.hw13.domain.Comment;
import ru.otus.spring.hw13.dto.CommentDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class CommentToCommentDtoConverter implements Converter<Comment, CommentDto> {

    @Override
    public Optional<CommentDto> convert(Comment comment) {
        return ofNullable(comment).map(this::map);
    }

    private CommentDto map(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText());
    }
}

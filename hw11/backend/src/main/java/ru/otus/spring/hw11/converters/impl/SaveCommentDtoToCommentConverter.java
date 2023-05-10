package ru.otus.spring.hw11.converters.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw11.converters.Converter;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.dto.SaveCommentDto;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class SaveCommentDtoToCommentConverter implements Converter<SaveCommentDto, Comment> {

    @Override
    public Optional<Comment> convert(SaveCommentDto saveCommentDto) {
        return ofNullable(saveCommentDto).map(this::map);
    }

    private Comment map(SaveCommentDto saveCommentDto) {
        return new Comment(saveCommentDto.getText(), saveCommentDto.getBookId());
    }
}

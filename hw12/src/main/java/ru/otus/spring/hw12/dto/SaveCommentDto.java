package ru.otus.spring.hw12.dto;

import lombok.Data;

@Data
public class SaveCommentDto {

    private String bookId;
    private String text;
}

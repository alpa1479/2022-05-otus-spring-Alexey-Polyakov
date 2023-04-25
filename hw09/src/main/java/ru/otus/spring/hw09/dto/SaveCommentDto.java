package ru.otus.spring.hw09.dto;

import lombok.Data;

@Data
public class SaveCommentDto {

    private String bookId;
    private String text;
}

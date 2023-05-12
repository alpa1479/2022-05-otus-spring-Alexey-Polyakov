package ru.otus.spring.hw13.dto;

import lombok.Data;

@Data
public class SaveCommentDto {

    private String bookId;
    private String text;
}

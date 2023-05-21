package ru.otus.spring.hw18.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommentDto {

    private String bookId;
    private String text;
}

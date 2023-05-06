package ru.otus.spring.hw11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private GenreDto genre;
    private List<AuthorDto> authors;
    private List<CommentDto> comments;
}

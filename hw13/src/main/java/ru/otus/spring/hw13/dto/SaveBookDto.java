package ru.otus.spring.hw13.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveBookDto {

    private String id;
    private String title;
    private String genreId;
    private List<String> authorIds;
}

package ru.otus.spring.hw08.shell.argumentmappers;

import ru.otus.spring.hw08.domain.Book;

import java.util.List;

public interface BookArgumentMapper {

    Book map(String title, String genreId, List<String> authorIds);

    Book map(String id, String title, String genreId, List<String> authorIds);
}

package ru.otus.spring.hw17.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.hw17.dto.AuthorDto;
import ru.otus.spring.hw17.dto.BookDto;
import ru.otus.spring.hw17.dto.GenreDto;
import ru.otus.spring.hw17.dto.SaveBookDto;
import ru.otus.spring.hw17.services.BooksService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookRestController.class, properties = "mongock.enabled=false")
class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BooksService booksService;

    @Test
    @DisplayName("should return all books")
    void shouldReturnAllBooks() throws Exception {
        List<BookDto> books = List.of(
                new BookDto("1", "Book 1", new GenreDto("1", "Genre 1"), List.of(new AuthorDto("1", "Author 1")), null),
                new BookDto("2", "Book 2", new GenreDto("2", "Genre 2"), List.of(new AuthorDto("2", "Author 2")), null)
        );
        when(booksService.findAll()).thenReturn(books);
        mvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(books)));
    }

    @Test
    @DisplayName("should return a book by id")
    void shouldReturnBookById() throws Exception {
        SaveBookDto book = new SaveBookDto("1", "Book 1", "Genre 1", List.of("Author 1"));
        when(booksService.findById("1")).thenReturn(Optional.of(book));
        mvc.perform(get("/api/v1/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

    @Test
    @DisplayName("should create a new book")
    void shouldCreateNewBook() throws Exception {
        SaveBookDto book = new SaveBookDto("1", "Book 1", "Genre 1", List.of("Author 1"));
        mvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should update a book")
    void shouldUpdateBook() throws Exception {
        SaveBookDto book = new SaveBookDto("1", "Book 1", "Genre 1", List.of("Author 1"));
        mvc.perform(put("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should delete a book by id")
    void shouldDeleteBookById() throws Exception {
        mvc.perform(delete("/api/v1/books/1")).andExpect(status().isNoContent());
    }
}
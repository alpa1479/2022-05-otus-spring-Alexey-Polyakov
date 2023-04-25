package ru.otus.spring.hw09.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.hw09.dto.*;
import ru.otus.spring.hw09.services.AuthorsService;
import ru.otus.spring.hw09.services.BooksService;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;
import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class, properties = "mongock.enabled=false")
class BookControllerTest {

    @MockBean
    private BooksService booksService;

    @MockBean
    private AuthorsService authorsService;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnAllBooksForBooksPage() throws Exception {
        List<BookDto> expectedBooks = getExpectedBooks();
        given(booksService.findAll()).willReturn(expectedBooks);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", expectedBooks));
    }

    @Test
    void shouldReturnBookModelForUpdateBookPage() throws Exception {
        SaveBookDto expectedBook = new SaveBookDto();
        given(booksService.findById("1")).willReturn(Optional.of(expectedBook));

        mvc.perform(get("/books/update?id=1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", expectedBook))
                .andExpect(model().attribute("comment", new SaveCommentDto()));
    }

    @Test
    void shouldReturnBookModelForCreateBookPage() throws Exception {
        List<AuthorDto> expectedAuthors = getExpectedAuthors();
        given(authorsService.findAll()).willReturn(expectedAuthors);

        mvc.perform(get("/books/create"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", new SaveBookDto()))
                .andExpect(model().attribute("allAuthors", expectedAuthors));
    }

    @Test
    void shouldCreateBook() throws Exception {
        mvc.perform(post("/books/create"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).create(any(SaveBookDto.class));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        mvc.perform(post("/books/update"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).update(any(SaveBookDto.class));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        mvc.perform(post("/books/delete?id=1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).deleteById(any(String.class));
    }

    @Test
    void shouldAddCommentToBook() throws Exception {
        mvc.perform(post("/books/comment"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).addComment(any(SaveCommentDto.class));
    }

    @Test
    void shouldDeleteBookComments() throws Exception {
        mvc.perform(post("/books/comments/delete?id=1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).deleteBookCommentsById(any(String.class));
    }

    @Test
    void shouldReturnErrorIfBookNotFoundForUpdatePage() throws Exception {
        given(booksService.findById("1")).willReturn(empty());

        mvc.perform(get("/books/update?id=1")).andExpect(status().isNotFound());
    }


    List<BookDto> getExpectedBooks() {
        return of(
                BookDto.builder().title("The Oblong Box").genre(new GenreDto(null, "drama")).build(),
                BookDto.builder().title("Skin Deep").genre(new GenreDto(null, "horror")).build(),
                BookDto.builder().title("Summer Catch").genre(new GenreDto(null, "thriller")).build()
        );
    }

    List<AuthorDto> getExpectedAuthors() {
        return of(
                new AuthorDto("1", "test"),
                new AuthorDto("2", "test1"),
                new AuthorDto("3", "test2")
        );
    }
}

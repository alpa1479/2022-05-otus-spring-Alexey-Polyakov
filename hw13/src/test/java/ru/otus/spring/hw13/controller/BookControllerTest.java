package ru.otus.spring.hw13.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.hw13.configuration.SecurityConfiguration;
import ru.otus.spring.hw13.dto.*;
import ru.otus.spring.hw13.services.AuthorsService;
import ru.otus.spring.hw13.services.BooksService;
import ru.otus.spring.hw13.services.GenresService;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;
import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfiguration.class)
@WebMvcTest(controllers = BookController.class, properties = "mongock.enabled=false")
class BookControllerTest {

    @MockBean
    private BooksService booksService;

    @MockBean
    private AuthorsService authorsService;

    @MockBean
    private GenresService genresService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithAnonymousUser
    void shouldAllowAccessToLoginPage() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void shouldRedirectAnonymousUserToLoginPage() throws Exception {
        mvc.perform(get("/books")
                        .header("Accept", "text/html"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void shouldRejectRequestToDeleteBookForUserRole() throws Exception {
        mvc.perform(post("/books/delete?id=1").with(csrf()))
                .andExpect(status().isForbidden());
        verifyNoInteractions(booksService);
    }

    @Test
    @WithAnonymousUser
    void shouldRedirectAfterLogin() throws Exception {
        mvc.perform(formLogin()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void shouldReturnAllBooksForBooksPage() throws Exception {
        List<BookDto> expectedBooks = getExpectedBooks();
        given(booksService.findAll()).willReturn(expectedBooks);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", expectedBooks));
    }

    @Test
    @WithMockUser
    void shouldReturnBookModelForUpdateBookPage() throws Exception {
        SaveBookDto expectedBook = new SaveBookDto();
        given(booksService.findById("1")).willReturn(Optional.of(expectedBook));

        mvc.perform(get("/books/update?id=1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", expectedBook))
                .andExpect(model().attribute("comment", new SaveCommentDto()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnBookModelForCreateBookPage() throws Exception {
        List<AuthorDto> expectedAuthors = getExpectedAuthors();
        given(authorsService.findAll()).willReturn(expectedAuthors);

        mvc.perform(get("/books/create"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", new SaveBookDto()))
                .andExpect(model().attribute("allAuthors", expectedAuthors));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateBook() throws Exception {
        mvc.perform(post("/books/create").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).create(any(SaveBookDto.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateBook() throws Exception {
        mvc.perform(post("/books/update").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).update(any(SaveBookDto.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteBook() throws Exception {
        mvc.perform(post("/books/delete?id=1").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).deleteById(any(String.class));
    }

    @Test
    @WithMockUser
    void shouldAddCommentToBook() throws Exception {
        mvc.perform(post("/books/comment").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).addComment(any(SaveCommentDto.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteBookComments() throws Exception {
        mvc.perform(post("/books/comments/delete?id=1").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/books"));

        verify(booksService).deleteBookCommentsById(any(String.class));
    }

    @Test
    @WithMockUser
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

package ru.otus.spring.hw18.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.hw18.dto.AuthorDto;
import ru.otus.spring.hw18.services.AuthorsService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorRestController.class, properties = "mongock.enabled=false")
class AuthorRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorsService authorsService;

    @Test
    @DisplayName("should return all authors")
    void shouldReturnAllAuthors() throws Exception {
        List<AuthorDto> authors = List.of(new AuthorDto("1", "Author1"), new AuthorDto("2", "Author2"));

        given(authorsService.findAll()).willReturn(authors);

        mvc.perform(get("/api/v1/authors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(authors)));
    }
}
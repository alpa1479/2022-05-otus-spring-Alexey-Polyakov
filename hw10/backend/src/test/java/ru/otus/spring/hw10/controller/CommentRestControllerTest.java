package ru.otus.spring.hw10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.hw10.dto.SaveCommentDto;
import ru.otus.spring.hw10.services.CommentsService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentRestController.class, properties = "mongock.enabled=false")
class CommentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentsService commentsService;

    @Test
    @DisplayName("should add a comment to book")
    void shouldAddCommentToBook() throws Exception {
        SaveCommentDto commentDto = new SaveCommentDto("bookId", "commentText");

        mvc.perform(post("/api/v1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentDto)))
                .andExpect(status().isCreated());

        verify(commentsService).save(commentDto);
        verifyNoMoreInteractions(commentsService);
    }

    @Test
    @DisplayName("should delete all comments for book")
    void shouldDeleteAllCommentsForBook() throws Exception {
        String bookId = "1";

        mvc.perform(delete("/api/v1/comments/books/{id}", bookId))
                .andExpect(status().isNoContent());

        verify(commentsService).deleteAllByBookId(bookId);
        verifyNoMoreInteractions(commentsService);
    }
}
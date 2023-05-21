package ru.otus.spring.hw18.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw18.converters.Converter;
import ru.otus.spring.hw18.domain.Book;
import ru.otus.spring.hw18.domain.Genre;
import ru.otus.spring.hw18.dto.BookDto;
import ru.otus.spring.hw18.dto.SaveBookDto;
import ru.otus.spring.hw18.repository.BookRepository;
import ru.otus.spring.hw18.repository.cascade.BookRepositoryCascade;
import ru.otus.spring.hw18.services.AuthorsService;
import ru.otus.spring.hw18.services.BooksService;
import ru.otus.spring.hw18.services.CommentsService;
import ru.otus.spring.hw18.services.GenresService;

import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Given books service impl")
@SpringBootTest(classes = {BooksServiceImpl.class})
class BooksServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookRepositoryCascade bookRepositoryCascade;

    @MockBean
    private GenresService genresService;

    @MockBean
    private AuthorsService authorsService;

    @MockBean
    private CommentsService commentsService;

    @MockBean
    private Converter<Book, BookDto> toBookDtoConverter;

    @MockBean
    private Converter<Book, SaveBookDto> toSaveBookDtoConverter;

    @MockBean
    private Converter<SaveBookDto, Book> toBookConverter;

    @Autowired
    private BooksService booksService;

    @Test
    @DisplayName("should call BookRepository to find all books")
    void shouldCallBookRepositoryToFindAllBooks() {
        booksService.findAll();
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("should call BookRepository to find book by id")
    void shouldCallBookRepositoryToFindBookById() {
        booksService.findById("1");
        verify(bookRepository).findById("1");
    }

    @Test
    @DisplayName("should call BookRepository to save book")
    void shouldCallBookRepositoryToCreateBook() {
        Book book = new Book("book", new Genre("1"));
        SaveBookDto saveBookDto = SaveBookDto.builder().title("book").genreId("1").build();

        when(toBookConverter.convert(saveBookDto)).thenReturn(of(book));
        booksService.create(saveBookDto);

        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("should call BookRepositoryCascade to delete book by id")
    void shouldCallBookRepositoryToDeleteBookById() {
        booksService.deleteById("1");
        verify(bookRepositoryCascade).cascadeDeleteById("1");
    }
}
package ru.otus.spring.hw09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.hw09.dto.SaveBookDto;
import ru.otus.spring.hw09.dto.SaveCommentDto;
import ru.otus.spring.hw09.exceptions.NotFoundException;
import ru.otus.spring.hw09.services.AuthorsService;
import ru.otus.spring.hw09.services.BooksService;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BooksService booksService;
    private final AuthorsService authorsService;

    @GetMapping
    public String booksPage(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books";
    }

    @GetMapping("/create")
    public String createBookPage(Model model) {
        model.addAttribute("book", new SaveBookDto());
        model.addAttribute("allAuthors", authorsService.findAll());
        return "create-book";
    }

    @GetMapping("/update")
    public String updateBookPage(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("book", booksService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("allAuthors", authorsService.findAll());
        model.addAttribute("comment", new SaveCommentDto());
        return "update-book";
    }

    @PostMapping("/create")
    public String createBook(SaveBookDto book) {
        booksService.create(book);
        return "redirect:/books";
    }

    @PostMapping("/comment")
    public String addBookComment(SaveCommentDto saveCommentDto) {
        booksService.addComment(saveCommentDto);
        return "redirect:/books";
    }

    @PostMapping("/update")
    public String updateBook(SaveBookDto book) {
        booksService.update(book);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam(value = "id") String id) {
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/comments/delete")
    public String deleteBookComments(@RequestParam(value = "id") String bookId) {
        booksService.deleteBookCommentsById(bookId);
        return "redirect:/books";
    }
}

package ru.otus.spring.hw13.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String indexPage() {
        return "forward:/books";
    }

    @GetMapping("/forbidden")
    public String forbiddenPage() {
        SecurityContext context = SecurityContextHolder.getContext();
        return "forbidden";
    }
}

package ru.otus.spring.hw15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.hw15.services.SpringIntegrationDemoService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var demoService = context.getBean(SpringIntegrationDemoService.class);
        demoService.start();
    }
}

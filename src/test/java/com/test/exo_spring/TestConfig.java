package com.test.exo_spring;

import com.test.exo_spring.objet.TodoItem;
import com.test.exo_spring.repo.TodoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@TestConfiguration
public class TestConfig {

    @Bean
    public CommandLineRunner dataLoader(TodoItemRepository repository) {
        return args -> {
            repository.saveAll(Arrays.asList(
                new TodoItem("Task 1", "Description 1", LocalDateTime.of(2023, 10, 1, 10, 0)),
                new TodoItem("Task 2", "Description 2", LocalDateTime.of(2023, 10, 2, 11, 0)),
                new TodoItem("Task 3", "Description 3", LocalDateTime.of(2023, 10, 3, 12, 0)),
                new TodoItem("Task 4", "Description 4", LocalDateTime.of(2023, 10, 4, 13, 0)),
                new TodoItem("Task 5", "Description 5", LocalDateTime.of(2023, 10, 5, 14, 0)),
                new TodoItem("Task 6", "Description 6", LocalDateTime.of(2023, 10, 6, 15, 0)),
                new TodoItem("Task 7", "Description 7", LocalDateTime.of(2023, 10, 7, 16, 0)),
                new TodoItem("Task 8", "Description 8", LocalDateTime.of(2023, 10, 8, 17, 0)),
                new TodoItem("Task 9", "Description 9", LocalDateTime.of(2023, 10, 9, 18, 0)),
                new TodoItem("Task 10", "Description 10", LocalDateTime.of(2023, 10, 10, 19, 0))
            ));
        };
    }
}
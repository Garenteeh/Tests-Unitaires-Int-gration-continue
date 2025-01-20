package com.test.exo_spring;

import com.test.exo_spring.objet.TodoItem;
import com.test.exo_spring.repo.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/todos")
    public List<TodoItem> getTodos() {
        return todoItemRepository.findAll();
    }

}

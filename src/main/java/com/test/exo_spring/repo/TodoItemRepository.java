package com.test.exo_spring.repo;

import com.test.exo_spring.objet.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}

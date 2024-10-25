package com.test.exo_spring;

import com.test.exo_spring.objet.TodoItem;
import com.test.exo_spring.objet.TodoList;
import com.test.exo_spring.objet.User;
import com.test.exo_spring.service.EmailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoListTest {

    private TodoList todoList;
    private EmailSenderService emailSenderService;
    private User user;

    @BeforeEach
    void setUp() {
        emailSenderService = mock(EmailSenderService.class);
        user = new User("John", "Doe", "john.doe@gmail.com", LocalDate.of(2000, 1, 1), "Password123");
        todoList = new TodoList(emailSenderService, user);
    }

    @Test
    void testSave() {
        TodoItem item = new TodoItem("Buy milk", "Buy milk at the grocery store", LocalDateTime.now());
        todoList.save(item);
        assertEquals(1, todoList.getItems().size());
    }

    @Test
    void testSaveTooFast() {
        TodoItem item = new TodoItem("Buy milk", "Buy milk at the grocery store", LocalDateTime.now());
        todoList.save(item);
        TodoItem item2 = new TodoItem("Buy bread", "Buy bread at the bakery", LocalDateTime.now());
        assertThrows(IllegalStateException.class, () -> todoList.save(item2));
    }

    @Test
    void testSaveEmail() {
        TodoList spyTodoList = spy(todoList);

        for (int i = 1; i < 8; i++) {
            spyTodoList.setLastCreationTime(LocalDateTime.now().minusHours(10));
            spyTodoList.save(new TodoItem("Task " + i, "Content " + i, LocalDateTime.now()));
        }
        spyTodoList.setLastCreationTime(LocalDateTime.now().minusMinutes(31));
        spyTodoList.save(new TodoItem("Task 8", "Content 8", LocalDateTime.now()));

        verify(emailSenderService, times(1)).sendEmail(eq(user.getEmail()), anyString(), anyString());
    }

    @Test
    void testUserTwoTodoLists() {
        assertThrows(IllegalStateException.class, () -> new TodoList(emailSenderService, user));
    }

    @Test
    void testTodoListFull() {
        TodoList spyTodoList = spy(todoList);
        for (int i = 0; i < 10; i++) {
            spyTodoList.setLastCreationTime(LocalDateTime.now().minusHours(10));
            spyTodoList.save(new TodoItem("Task " + i, "Content " + i, LocalDateTime.now()));
        }
        assertThrows(IllegalStateException.class, () -> spyTodoList.save(new TodoItem("Task 11", "Content 11", LocalDateTime.now())));
    }

    @Test
    void testUniqueItemTitles() {
        TodoItem item1 = new TodoItem("Task", "Content", LocalDateTime.now());
        todoList.save(item1);
        TodoItem item2 = new TodoItem("Task", "Different Content", LocalDateTime.now().plusMinutes(31));
        assertThrows(IllegalStateException.class, () -> todoList.save(item2));
    }

    @Test
    void testItemDescriptionLength() {
        String longDescription = "a".repeat(1001);
        TodoItem item = new TodoItem("Task", longDescription, LocalDateTime.now());
        assertThrows(IllegalStateException.class, () -> todoList.save(item));
    }


}
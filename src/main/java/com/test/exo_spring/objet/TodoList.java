package com.test.exo_spring.objet;

import com.test.exo_spring.service.EmailSenderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoList {
    private static Map<User, TodoList> userTodoListMap = new HashMap<>();
    private List<TodoItem> items;
    private LocalDateTime lastCreationTime;
    private EmailSenderService emailSenderService;
    private User user;

    public TodoList(EmailSenderService emailSenderService, User user) {
        if (userTodoListMap.containsKey(user)) {
            throw new IllegalStateException("User already has a TodoList");
        }
        this.items = new ArrayList<>();
        this.emailSenderService = emailSenderService;
        this.user = user;
        userTodoListMap.put(user, this);
    }

    public TodoList() {
        this.items = new ArrayList<>();
    }

    public void save(TodoItem item) {
        if (items.size() >= 10) {
            throw new IllegalStateException("Todo list is full");
        }
        if (lastCreationTime != null && lastCreationTime.plusMinutes(30).isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("You must wait 30 minutes between two items creation");
        }
        for (TodoItem existingItem : items) {
            if (existingItem.getTitle().equals(item.getTitle())) {
                throw new IllegalStateException("Todo item name must be unique");
            }
        }
        if (item.getDescription().length() > 1000) {
            item.setDescription(item.getDescription().substring(0, 1000));
            throw new IllegalStateException("Todo item content must not exceed 1000 characters");
        }
        items.add(item);
        lastCreationTime = LocalDateTime.now();

        if (items.size() == 8) {
            emailSenderService.sendEmail(user.getEmail(), "Todo List Full", "Your todo list is full now.");
        }
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public void setItems(List<TodoItem> items) {
        this.items = items;
    }

    public LocalDateTime getLastCreationTime() {
        return lastCreationTime;
    }

    public void setLastCreationTime(LocalDateTime lastCreationTime) {
        this.lastCreationTime = lastCreationTime;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
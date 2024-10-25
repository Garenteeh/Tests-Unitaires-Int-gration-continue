package com.test.exo_spring.objet;

import java.time.LocalDateTime;

public class TodoItem {

    private String title;
    private String description;
    private boolean done;

    private LocalDateTime creationDate;

    public TodoItem(String title, String description, LocalDateTime creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.done = false;
    }

    public TodoItem() {
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}

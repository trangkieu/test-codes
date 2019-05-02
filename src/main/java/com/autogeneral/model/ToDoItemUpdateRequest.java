package com.autogeneral.model;

public class ToDoItemUpdateRequest {
    private String text;
    private boolean isCompleted;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

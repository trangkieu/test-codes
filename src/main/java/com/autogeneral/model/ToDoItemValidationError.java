package com.autogeneral.model;

public class ToDoItemValidationError {
    private ErrorMessageDetails details;
    private String name;

    public ErrorMessageDetails getDetails() {
        return details;
    }

    public void setDetails(ErrorMessageDetails details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

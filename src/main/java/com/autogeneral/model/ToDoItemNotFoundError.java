package com.autogeneral.model;

import java.util.List;

public class ToDoItemNotFoundError {
    private List<ErrorMessage> details;
    private String name;

    public List<ErrorMessage> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorMessage> details) {
        this.details = details;
    }
}

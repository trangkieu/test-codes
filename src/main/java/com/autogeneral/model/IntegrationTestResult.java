package com.autogeneral.model;

public class IntegrationTestResult {
    private ToDoItem bracers;
    private ToDoItem todo;
    private boolean isCorrect;

    public ToDoItem getBracers() {
        return bracers;
    }

    public void setBracers(ToDoItem bracers) {
        this.bracers = bracers;
    }

    public ToDoItem getTodo() {
        return todo;
    }

    public void setTodo(ToDoItem todo) {
        this.todo = todo;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

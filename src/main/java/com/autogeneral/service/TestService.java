package com.autogeneral.service;

import com.autogeneral.model.ToDoItem;
import com.autogeneral.repo.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {
    @Autowired
    public TestService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public ToDoItem createOrUpdate(ToDoItem toDoItem) {
        toDoItemRepository.save(toDoItem);
        return toDoItem;
    }

    public Iterable<ToDoItem> list() {
        return toDoItemRepository.findAll();
    }

    public Optional<ToDoItem> find(int itemNumber) {
        return toDoItemRepository.findById(itemNumber);
    }

    private ToDoItemRepository toDoItemRepository;
}

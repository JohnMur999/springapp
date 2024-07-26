package com.johnmur.springapp.service;

import com.johnmur.springapp.entity.TodoItem;
import com.johnmur.springapp.entity.User;
import com.johnmur.springapp.repos.TodoItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepo todoItemRepo;

    public List<TodoItem> getAllTodos(User user) {
        return todoItemRepo.findByUser(user);
    }
    public Optional<TodoItem> getTodoById(Long id) {
        return todoItemRepo.findById(id);
    }
    public TodoItem saveTodoItem(TodoItem todoItem) {
        return todoItemRepo.save(todoItem);
    }
    public void deleteTodo(Long todoItem) {
        todoItemRepo.deleteById(todoItem);
    }
}

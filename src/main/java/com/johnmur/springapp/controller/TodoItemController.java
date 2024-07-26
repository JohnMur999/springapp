package com.johnmur.springapp.controller;

import com.johnmur.springapp.entity.TodoItem;
import com.johnmur.springapp.entity.User;
import com.johnmur.springapp.service.TodoItemService;
import com.johnmur.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoItemController {
    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getTodos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<TodoItem> todos = todoItemService.getAllTodos(user);
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/new")
    public String createTodo(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute TodoItem todoItem) {
        User user = userService.findByUsername(userDetails.getUsername());
        todoItem.setUser(user);
        todoItemService.saveTodoItem(todoItem);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        TodoItem todoItem = todoItemService.getTodoById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        model.addAttribute("todo", todoItem);
        return "edit_todo";
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute TodoItem todoItem, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        todoItem.setUser(user);
        todoItem.setId(id);
        todoItemService.saveTodoItem(todoItem);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoItemService.deleteTodo(id);
        return "redirect:/todos";
    }
}

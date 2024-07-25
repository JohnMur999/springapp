package com.johnmur.springapp.repos;

import com.johnmur.springapp.entity.TodoItem;
import com.johnmur.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepo extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUser(User user);
}

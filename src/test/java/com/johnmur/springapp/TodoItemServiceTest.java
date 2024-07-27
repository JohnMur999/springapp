package com.johnmur.springapp;

import com.johnmur.springapp.entity.TodoItem;
import com.johnmur.springapp.entity.User;
import com.johnmur.springapp.repos.TodoItemRepo;
import com.johnmur.springapp.service.TodoItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoItemServiceTest {

    @MockBean
    private TodoItemRepo todoItemRepo;

    @Autowired
    private TodoItemService todoItemService;

    @Test
    public void testGetAllTodos() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setDescription("test description");
        todoItem.setUser(user);

        when(todoItemRepo.findByUser(user))
                .thenReturn(Collections.singletonList(todoItem));
        List<TodoItem> todos = todoItemService.getAllTodos(user);

        assertEquals(1, todos.size());
        assertEquals("test description", todos.get(0).getDescription());
    }

}

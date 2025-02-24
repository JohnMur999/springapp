package com.johnmur.springapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    private String roles;

    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TodoItem> todoItems;
}

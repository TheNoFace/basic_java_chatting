package com.example.demo.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    
    private String password;


    @OneToMany(mappedBy = "user")
    private List<ChatMessage> chatMessages;

    // @OneToMany(mappedBy = "user")
    // private List<Notice> notices;

    // @OneToMany(mappedBy = "user")
    // private List<Task> tasks;
    // @OneToMany(mappedBy = "user")
    // private List<Notice> notices;

    // @OneToMany(mappedBy = "user")
    // private List<Task> tasks;

}
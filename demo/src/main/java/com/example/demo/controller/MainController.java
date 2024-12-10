package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.ChatRoom;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService ; 

    @Autowired
    ChatService chatService ; 

    

    @GetMapping("/")
    public String home() {
        return "index";  
    }

    @GetMapping("/login")
    public String login() {
        return "login";  
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";  
    }
}


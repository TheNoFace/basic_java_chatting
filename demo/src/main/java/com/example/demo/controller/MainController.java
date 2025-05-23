package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService ; 

    @Autowired
    ChatService chatService ; 

    // 기본 Get
    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "login";  
    }

    @GetMapping("/signup") 
    public String signup() {
        return "signup";  
    }

    // 로그인 후 메인 
    @GetMapping("/maingroupware")
    public String mainPage(@SessionAttribute("loginUser") User user, Model model) {
        String username = user.getUsername(); 
        List<User> userList = userService.getAllUsersExcept(username); 
        model.addAttribute("userList", userList);
        model.addAttribute("loggedInUsername", username);
    
        return "maingroupware";
    }


    
    


}


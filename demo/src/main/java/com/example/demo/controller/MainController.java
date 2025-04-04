package com.example.demo.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService ; 

    @Autowired
    ChatService chatService ; 

    @Autowired
    NoticeService noticeService ; 

    @Autowired
    TaskService taskService ; 


    

    // @GetMapping("/")
    // public String home() {
    //     return "index";  
    // }

    // @GetMapping("/login")
    // public String login() {
    //     return "login";  
    // }

    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "login";  
    }

    // @PostMapping()
    // public 

    // @PostMapping()
    // public List<User> selectUser(){

    //     return "" ; 
    // }


    @GetMapping("/signup") // admin에서 사용
    public String signup() {
        return "signup";  
    }

    @GetMapping("/maingroupware")
    public String mainPage(@SessionAttribute("loginUser") User user, Model model, Principal principal) {
        String username = principal.getName(); // 로그인한 사용자
        System.out.println("조회된 사용자 수: " + username);
        List<User> userList = userService.getAllUsersExcept(username); 
        System.out.println("조회된 사용자 수: " + userList.size());
   
        model.addAttribute("userList", userList);
        model.addAttribute("loggedInUsername", username);

        model.addAttribute("chatList", chatService.getRecentChats(user.getId()));
        model.addAttribute("noticeList", noticeService.getRecentNotices());
        model.addAttribute("todoList", taskService.getMyTasks(user.getId()));
        return "maingroupware";
    }

}


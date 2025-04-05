package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    
    @PostMapping("/signup")
    public ModelAndView registerUser(User user) {
        userService.saveUser(user);
        return new  ModelAndView("login");
    }

    // 로그인
    @PostMapping(value = "/signin", 
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView authenticateUser(LoginRequest loginRequest, HttpSession session) {
    User user = userService.findByUsername(loginRequest.getUsername());
    session.setAttribute("loginUser", user);
    session.setAttribute("loginUsername", user.getUsername());

    return new ModelAndView("redirect:/maingroupware");
}


}


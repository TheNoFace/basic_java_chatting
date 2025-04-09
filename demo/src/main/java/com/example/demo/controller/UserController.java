package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    
    // 회원 가입
    @PostMapping("/signup")
    public ModelAndView registerUser(User user) {
        userService.saveUser(user);
        return new  ModelAndView("login");
    }

    // 로그인
    @PostMapping(value = "/signin")
    public ModelAndView authenticateUser(User users, HttpSession session) {

    User user = userService.findByUsernameAndByPassword(users.getUsername(),users.getPassword());
    session.setAttribute("loginUser", user);
    session.setAttribute("loginUsername", user.getUsername());

    return new ModelAndView("redirect:/maingroupware");
    }



    // 로그아웃
    @GetMapping("/logout")
    public ModelAndView logOut(HttpSession session){
            session.invalidate();
            return new ModelAndView("redirect:/login") ;
         
    }


}


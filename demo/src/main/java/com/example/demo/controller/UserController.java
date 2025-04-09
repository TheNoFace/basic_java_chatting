package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.model.*;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateEngine templateEngine;

    
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


    // 유저 찾음
    @PostMapping("/searchUsers")
    public String searchUsers(@RequestParam("keyword") String keyword,
                              Model model,
                              HttpSession session) {

        String currentUser = (String) session.getAttribute("loginUsername");
        List<User> filteredUsers = userService.searchByNameExcludingSelf(keyword, currentUser);
                            
        model.addAttribute("userList", filteredUsers);
        Context context = new Context();
        context.setVariable("userList", filteredUsers);
        return templateEngine.process("fragments/userList", context);

    }



    // 로그아웃
    @GetMapping("/logout")
    public ModelAndView logOut(HttpSession session){
            session.invalidate();
            return new ModelAndView("redirect:/login") ;
         
    }


}


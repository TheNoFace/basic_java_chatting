package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;

@Controller
public class NoticeController {


    @Autowired
    private NoticeRepository noticeRepository ; 

    @GetMapping("/notice/new")
    public String newNoticeForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice_form";
    }

    @PostMapping("/notice/new")
    public String createNotice(@ModelAttribute Notice notice) {
        notice.setCreatedAt(LocalDateTime.now());
        noticeRepository.save(notice);
        return "redirect:/main";
    }
}

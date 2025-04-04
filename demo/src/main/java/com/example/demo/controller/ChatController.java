package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatService;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;



    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                             SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


    @MessageMapping("/chat/send") // /app/chat/send
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {
        return message;
    }


    @GetMapping("/chat/history")
    @ResponseBody
    public List<ChatMessage> getChatHistory(
        @RequestParam String user1,
        @RequestParam String user2
    ) {
        return chatService.getChatHistoryBetween(user1, user2);
    }


}
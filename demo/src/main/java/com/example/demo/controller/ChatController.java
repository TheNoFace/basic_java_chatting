package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    public void handleMessage(@Payload ChatMessage message,
                              @Header("simpSessionAttributes") Map<String, Object> sessionAttributes) {

        ChatMessage saved = chatService.saveMessage(message);
        messagingTemplate.getUserDestinationPrefix();
        messagingTemplate.convertAndSend("/topic/messages/" + saved.getReceiver(), saved);
    
    }

    @GetMapping("/history/{sender}/{receiver}")
    @ResponseBody
    public List<ChatMessage> getChatHistory(@PathVariable String sender,
                                            @PathVariable String receiver) {
        return chatService.getChatHistory(sender, receiver);
    }
}
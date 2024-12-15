package com.example.demo.controller;

import org.springframework.stereotype.Controller;
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

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatService.saveAndReturnMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                             SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


    @MessageMapping("/chat.leaveRoom")
    @SendTo("/topic/public")
    public ChatMessage leaveRoom(@Payload ChatMessage chatMessage) {
        // roomId를 ChatMessage에서 가져오도록 수정
        String roomId = chatMessage.getChatRoom().getRoomId();
        chatService.leaveRoom(roomId, chatMessage.getSender());
        return chatMessage;
    }


}
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Chat;
import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatService {

    
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getRecentChats(Long userId) {
        return chatRepository.findTop5ByUserIdOrderByTimestampDesc(userId);
    }

    public List<ChatMessage> getChatHistoryBetween(String user1, String user2) {
        return chatRepository.findBySenderAndReceiver(user1, user2);
    }

}
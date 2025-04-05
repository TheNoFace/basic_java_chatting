package com.example.demo.service;

import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        message.setIsread(false);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getUnreadMessages(String receiver) {
        return chatMessageRepository.findByReceiverAndIsreadFalse(receiver);
    }

    public List<ChatMessage> getRecentMessages(String user1, String user2) {
        return chatMessageRepository.findTop20BySenderOrReceiverOrderByTimestampDesc(user1, user2);
    }

    public void markAsRead(List<ChatMessage> messages) {
        for (ChatMessage msg : messages) {
            msg.setIsread(true);
        }
        chatMessageRepository.saveAll(messages);
    }


    public List<ChatMessage> getChatHistory(String sender, String receiver) {
        return chatMessageRepository.findChatHistory(sender, receiver);
    }
}

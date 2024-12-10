package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.User;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    @Autowired
    private UserRepository userRepository;

    public ChatRoom createRoom(ChatRoom chatRoom) {
        chatRoom.setRoomId(UUID.randomUUID().toString());
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getRoom(String roomId) {
        return chatRoomRepository.findByRoomId(roomId)
            .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public ChatMessage saveAndReturnMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public void addUserToRoom(String roomId, String username) {
        ChatRoom room = getRoom(roomId);
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        room.getParticipants().add(user);
        chatRoomRepository.save(room);
    }

    public void removeUserFromRoom(String roomId, String username) {
        ChatRoom room = getRoom(roomId);
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        room.getParticipants().remove(user);
        chatRoomRepository.save(room);
    }
    
}
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

    // 채팅방 생성
    public ChatRoom createRoom(ChatRoom chatRoom) {
        if (chatRoom.getRoomcount() < 2) {
            throw new IllegalArgumentException("방 인원수는 최소 2명 이상이어야 합니다.");
        }

        chatRoom.setRoomId(UUID.randomUUID().toString());
        chatRoom.setCurrentUserCount(0);
        chatRoom.setFull(false);
        return chatRoomRepository.save(chatRoom);
    }

    // 모든 채팅방 조회
    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }

    // 특정 채팅방 조회
    public ChatRoom getRoom(String roomId) {
        return chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));
    }

    // 채팅방 입장 가능 여부 확인
    public boolean canJoinRoom(String roomId, String username) {
        ChatRoom room = getRoom(roomId);

        if (room.getCurrentUserCount() >= room.getRoomcount()) {
            return false;
        }

        return !room.getParticipants().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    // 채팅방 입장
    public void joinRoom(String roomId, String username) {
        ChatRoom room = getRoom(roomId);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (room.getCurrentUserCount() >= room.getRoomcount()) {
            throw new RuntimeException("채팅방이 가득 찼습니다.");
        }

        if (!room.getParticipants().contains(user)) {
            room.getParticipants().add(user);
            room.setCurrentUserCount(room.getCurrentUserCount() + 1);
            room.setFull(room.getCurrentUserCount() >= room.getRoomcount());
            chatRoomRepository.save(room);
        }
    }

    // 채팅방 퇴장
    public void leaveRoom(String roomId, String username) {
        ChatRoom room = getRoom(roomId);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (room.getParticipants().remove(user)) {
            room.setCurrentUserCount(room.getCurrentUserCount() - 1);
            room.setFull(false);
            chatRoomRepository.save(room);
        }
    }

    // 채팅 메시지 저장
    public ChatMessage saveAndReturnMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }
}
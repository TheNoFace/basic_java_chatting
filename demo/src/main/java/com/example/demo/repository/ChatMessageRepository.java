package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatRoom;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomOrderByTimestampDesc(ChatRoom chatRoom);
    List<ChatMessage> findByChatRoomAndTimestampAfter(ChatRoom chatRoom, LocalDateTime timestamp);
}

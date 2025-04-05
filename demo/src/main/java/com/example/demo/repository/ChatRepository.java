package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Chat;
import com.example.demo.model.ChatMessage;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    List<Chat> findTop5ByUserIdOrderByTimestampDesc(Long userId);


    List<ChatMessage> findByReceiverAndIsreadFalse(String receiver);
    List<ChatMessage> findTop20BySenderOrReceiverOrderByTimestampDesc(String sender, String receiver);


    
    @Query("SELECT c FROM ChatMessage c WHERE " +
           "(c.sender = :sender AND c.receiver = :receiver) OR " +
           "(c.sender = :receiver AND c.receiver = :sender) " +
           "ORDER BY c.timestamp ASC")
    List<ChatMessage> findChatHistory(String sender, String receiver);

}



package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Chat;
import com.example.demo.model.ChatMessage;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findTop5ByUserIdOrderByTimestampDesc(Long userId);


@Query("SELECT m FROM ChatMessage m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp ASC")
List<ChatMessage> findBySenderAndReceiver(@Param("user1") String user1, @Param("user2") String user2);

}



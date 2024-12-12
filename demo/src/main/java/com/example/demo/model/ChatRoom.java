package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "room_id")
    private String roomId;

    private String name; // 방 이름
    
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> messages = new ArrayList<>();
    
    @ManyToMany
    private Set<User> participants = new HashSet<>();

    @Column(name = "room_count") // 인원수 설정
    private int roomcount ;
    
    
    // private int currentUserCount = 0;           // 현재 인원수 
    // private Set<String> users = new HashSet<>(); // 현재 참여중인 인원


    // public static ChatRoom create(String name, int roomcount) {
    //     ChatRoom chatRoom = new ChatRoom();
    //     chatRoom.roomId = UUID.randomUUID().toString();
    //     chatRoom.name = name;
    //     chatRoom.roomcount = roomcount;
    //     chatRoom.currentUserCount = 0;
    //     return chatRoom;
    // }

    // // 사용자 입장
    // public boolean addUser(String username) {
    //     if (isFull()) {
    //         return false;
    //     }
    //     if (users.add(username)) {
    //         currentUserCount++;
    //         return true;
    //     }
    //     return false;
    // }

    // // 사용자 퇴장
    // public boolean removeUser(String username) {
    //     if (users.remove(username)) {
    //         currentUserCount--;
    //         return true;
    //     }
    //     return false;
    // }

    // // 방이 가득 찼는지 확인
    // public boolean isFull() {
    //     return currentUserCount >= roomcount;
    // }

    // // 특정 사용자가 방에 있는지 확인
    // public boolean containsUser(String username) {
    //     return users.contains(username);
    // }


}

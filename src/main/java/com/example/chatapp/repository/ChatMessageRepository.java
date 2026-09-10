package com.example.chatapp.repository;

import com.example.chatapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // Fetch chat history for a room, oldest first
    List<ChatMessage> findByRoomIdOrderBySentAtAsc(String roomId);
}

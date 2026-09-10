package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.repository.ChatMessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ChatHistoryController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatHistoryController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    // GET /api/rooms/{roomId}/messages -> full history, oldest first
    @GetMapping("/{roomId}/messages")
    public List<ChatMessage> getHistory(@PathVariable String roomId) {
        return chatMessageRepository.findByRoomIdOrderBySentAtAsc(roomId);
    }
}

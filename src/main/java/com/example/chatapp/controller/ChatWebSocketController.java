package com.example.chatapp.controller;

import com.example.chatapp.dto.ChatMessageDto;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class ChatWebSocketController {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(ChatMessageRepository chatMessageRepository,
                                    SimpMessagingTemplate messagingTemplate) {
        this.chatMessageRepository = chatMessageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // Client sends to: /app/chat/{roomId}
    // Server broadcasts to subscribers of: /topic/room/{roomId}
    @MessageMapping("/chat/{roomId}")
    public void handleChatMessage(@DestinationVariable String roomId, ChatMessageDto incoming) {
        Instant now = Instant.now();

        if (incoming.getType() == ChatMessageDto.MessageType.CHAT) {
            // Persist chat messages to PostgreSQL
            ChatMessage entity = new ChatMessage(roomId, incoming.getSender(), incoming.getContent(), now);
            chatMessageRepository.save(entity);
        }

        incoming.setRoomId(roomId);
        incoming.setSentAt(now.toString());

        // Broadcast to everyone (both participants) subscribed to this room
        messagingTemplate.convertAndSend("/topic/room/" + roomId, incoming);
    }
}

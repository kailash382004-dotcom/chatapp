package com.example.chatapp.dto;

import jakarta.validation.constraints.NotBlank;

public class ChatMessageDto {

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @NotBlank
    private String roomId;

    @NotBlank
    private String sender;

    private String content;

    private MessageType type = MessageType.CHAT;

    private String sentAt; // ISO-8601 string, filled server-side on broadcast

    public ChatMessageDto() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}

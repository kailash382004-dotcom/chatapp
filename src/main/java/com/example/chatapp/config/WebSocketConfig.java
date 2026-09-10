package com.example.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Messages sent TO clients go through /topic (broadcast to subscribers of a room)
        registry.enableSimpleBroker("/topic");
        // Messages sent FROM clients to the server are prefixed with /app
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Clients connect here. SockJS fallback for browsers/networks without raw WebSocket support.
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*") // tighten this in production
                .withSockJS();
    }
}

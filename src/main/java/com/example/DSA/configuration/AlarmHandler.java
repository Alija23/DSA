package com.example.DSA.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AlarmHandler extends TextWebSocketHandler {

    // Store active sessions
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
    private static ObjectMapper objectMapper = new ObjectMapper(); // For JSON conversion

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // Add session to the set
        System.out.println("New client connected: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message from client: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session); // Remove session from the set
        System.out.println("Client disconnected: " + session.getId());
    }

    // Method to broadcast messages to all connected clients
    public static void broadcastMessage(String message) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void broadcastAlarmModel(Object alarmModel) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(alarmModel); // Convert to JSON
            synchronized (sessions) {
                for (WebSocketSession session : sessions) {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(jsonMessage));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

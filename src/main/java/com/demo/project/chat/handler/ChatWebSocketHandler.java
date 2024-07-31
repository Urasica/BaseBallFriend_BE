package com.demo.project.chat.handler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, List<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String roomId = extractRoomIdFromSession(session);
        if (roomId != null) {
            session.getAttributes().put("roomId", roomId);
            chatRooms.computeIfAbsent(roomId, k -> new CopyOnWriteArrayList<>()).add(session);
            System.out.println("New connection established in room " + roomId);
        } else {
            System.err.println("Room ID is null. Connection not added.");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String roomId = (String) session.getAttributes().get("roomId");
        if (roomId != null) {
            List<WebSocketSession> sessions = chatRooms.get(roomId);
            if (sessions != null) {
                for (WebSocketSession wsSession : sessions) {
                    if (wsSession.isOpen()) {
                        wsSession.sendMessage(message);
                        System.out.println("Sent message to room " + roomId + ": " + message.getPayload());
                    }
                }
            } else {
                System.err.println("No sessions found for room " + roomId);
            }
        } else {
            System.err.println("Room ID is null. Cannot handle message.");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String roomId = (String) session.getAttributes().get("roomId");
        if (roomId != null) {
            List<WebSocketSession> sessions = chatRooms.get(roomId);
            if (sessions != null) {
                sessions.remove(session);
                if (sessions.isEmpty()) {
                    chatRooms.remove(roomId);
                }
            }
            System.out.println("Connection closed in room " + roomId);
        } else {
            System.err.println("Room ID is null. Connection not removed.");
        }
    }

    private String extractRoomIdFromSession(WebSocketSession session) {
        if (session.getUri() != null) {
            String uri = session.getUri().toString();
            String[] parts = uri.split("/");
            if (parts.length >= 4) {
                return parts[parts.length - 1]; // Last part of the path
            }
        }
        return null;
    }
}
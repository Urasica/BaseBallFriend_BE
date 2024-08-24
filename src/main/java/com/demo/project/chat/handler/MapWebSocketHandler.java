package com.demo.project.chat.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, List<WebSocketSession>> mapRooms = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper 사용

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws IOException {
        String roomId = extractRoomIdFromSession(session); // 방 ID 추출
        if (roomId != null) {
            session.getAttributes().put("roomId", roomId);
            mapRooms.computeIfAbsent(roomId, k -> new CopyOnWriteArrayList<>()).add(session);

            // 유저 리스트 전송
            sendUserList(roomId, session);

            // 접속한 유저 정보 전송
            Map<String, Object> joinMessage = Map.of(
                    "type", "user-joined",
                    "nickname", session.getId() // session.getId() 대신 유저 식별자를 사용
            );
            broadcastToRoom(roomId, objectMapper.writeValueAsString(joinMessage));

            System.out.println("New connection established in map room " + roomId);
        } else {
            System.err.println("Room ID is null. Connection not added.");
        }
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        try {
            String payload = message.getPayload();
            Map<String, Object> jsonMessage = objectMapper.readValue(payload, new TypeReference<>() {
            });
            String type = (String) jsonMessage.get("type");
            String roomId = (String) session.getAttributes().get("roomId");

            if ("chat".equals(type)) {
                handleChatMessage(roomId, jsonMessage);
            } else if ("move".equals(type)) {
                handleMoveMessage(roomId, jsonMessage);
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
            try {
                session.sendMessage(new TextMessage("{\"type\": \"error\", \"message\": \"Invalid message format.\"}"));
            } catch (IOException ioException) {
                System.err.println("Failed to send error message: " + ioException.getMessage());
            }
        }
    }

    private void handleChatMessage(String roomId, Map<String, Object> jsonMessage) throws IOException {
        String nickname = (String) jsonMessage.get("nickname");
        String message = (String) jsonMessage.get("message");

        // 방 안의 모든 세션에 채팅 메시지를 전달
        broadcastToRoom(roomId, objectMapper.writeValueAsString(jsonMessage));
        System.out.println(nickname + " sent chat message: " + message + " in room " + roomId);
    }

    private void handleMoveMessage(String roomId, Map<String, Object> jsonMessage) throws IOException {
        String nickname = (String) jsonMessage.get("nickname");
        int x = (int) jsonMessage.get("x");
        int y = (int) jsonMessage.get("y");

        // 방 안의 모든 세션에 이동 메시지를 전달
        broadcastToRoom(roomId, objectMapper.writeValueAsString(jsonMessage));
        System.out.println(nickname + " moved to x: " + x + ", y: " + y + " in room " + roomId);
    }

    private void broadcastToRoom(String roomId, String message) throws IOException {
        List<WebSocketSession> sessions = mapRooms.get(roomId);
        if (sessions != null) {
            for (WebSocketSession wsSession : sessions) {
                if (wsSession.isOpen()) {
                    wsSession.sendMessage(new TextMessage(message));
                }
            }
        } else {
            System.err.println("No sessions found for room " + roomId);
        }
    }

    private void sendUserList(String roomId, WebSocketSession session) throws IOException {
        List<WebSocketSession> sessions = mapRooms.get(roomId);
        if (sessions != null) {
            List<String> userList = new CopyOnWriteArrayList<>();
            for (WebSocketSession s : sessions) {
                userList.add(s.getId()); // session.getId() 대신 닉네임이나 다른 식별자 사용 가능
            }
            Map<String, Object> userListMessage = Map.of(
                    "type", "user-list",
                    "users", userList
            );
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(userListMessage)));
        }
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws IOException {
        String roomId = (String) session.getAttributes().get("roomId");
        if (roomId != null) {
            removeSessionFromRoom(roomId, session);

            // 접속 종료한 유저 정보 전송
            Map<String, Object> leaveMessage = Map.of(
                    "type", "user-left",
                    "nickname", session.getId() // session.getId() 대신 유저 식별자를 사용
            );
            broadcastToRoom(roomId, objectMapper.writeValueAsString(leaveMessage));

            System.out.println("Connection closed in map room " + roomId);
        } else {
            System.err.println("Room ID is null. Connection not removed.");
        }
    }

    private void removeSessionFromRoom(String roomId, WebSocketSession session) {
        List<WebSocketSession> sessions = mapRooms.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                mapRooms.remove(roomId);
            }
        }
    }

    private String extractRoomIdFromSession(WebSocketSession session) {
        if (session.getUri() != null) {
            String uri = session.getUri().toString();
            String[] parts = uri.split("/");
            if (parts.length >= 4) {
                return parts[parts.length - 1]; // URL의 마지막 부분을 방 ID로 사용
            }
        }
        return null;
    }
}
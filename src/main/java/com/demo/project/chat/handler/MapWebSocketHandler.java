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
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        String roomId = extractRoomIdFromSession(session);
        if (roomId != null) {
            session.getAttributes().put("roomId", roomId);
            session.getAttributes().putIfAbsent("nickname", session.getId()); // 기본 닉네임 설정

            mapRooms.computeIfAbsent(roomId, k -> new CopyOnWriteArrayList<>()).add(session);

            // 이후, 사용자가 닉네임을 설정하면 사용자 목록이 업데이트됨
            System.out.println("New connection established in map room " + roomId);
        } else {
            System.err.println("Room ID is null. Connection not added.");
        }
    }


    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        try {
            String payload = message.getPayload();
            Map<String, Object> jsonMessage = objectMapper.readValue(payload, new TypeReference<>() {});
            String type = (String) jsonMessage.get("type");
            String roomId = (String) session.getAttributes().get("roomId");

            if ("set-nickname".equals(type)) {
                handleSetNicknameMessage(roomId, jsonMessage, session);
            } else if ("chat".equals(type)) {
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

    private void handleSetNicknameMessage(String roomId, Map<String, Object> jsonMessage, WebSocketSession session) throws IOException {
        // 닉네임 설정
        String nickname = (String) jsonMessage.get("nickname");
        if (nickname != null && !nickname.trim().isEmpty()) {
            session.getAttributes().put("nickname", nickname);
        } else {
            session.getAttributes().putIfAbsent("nickname", session.getId()); // 기본 닉네임
        }

        // 현재 방의 모든 클라이언트에게 새로운 사용자 목록을 전송
        sendUserListToAllInRoom(roomId);
    }

    private void sendUserListToAllInRoom(String roomId) throws IOException {
        List<WebSocketSession> sessions = mapRooms.get(roomId);
        if (sessions != null) {
            // 방에 있는 모든 클라이언트에게 사용자 목록 전송
            List<String> userList = new CopyOnWriteArrayList<>();
            for (WebSocketSession s : sessions) {
                String nickname = (String) s.getAttributes().get("nickname");
                if (nickname != null && !nickname.trim().isEmpty()) {
                    userList.add(nickname);
                } else {
                    userList.add(s.getId()); // 기본 닉네임이 세션 ID인 경우
                }
            }

            Map<String, Object> userListMessage = Map.of(
                    "type", "user-list",
                    "users", userList
            );

            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(objectMapper.writeValueAsString(userListMessage)));
                }
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

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws IOException {
        String roomId = (String) session.getAttributes().get("roomId");
        if (roomId != null) {
            removeSessionFromRoom(roomId, session);

            // 접속 종료한 유저 정보 전송
            Map<String, Object> leaveMessage = Map.of(
                    "type", "user-left",
                    "nickname", session.getAttributes().get("nickname") // 닉네임 사용
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
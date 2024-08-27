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
    private final Map<String, Map<String, Integer>> userCoordinates = new ConcurrentHashMap<>(); // 좌표 저장
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper 사용

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        String roomId = extractRoomIdFromSession(session);
        if (roomId != null) {
            session.getAttributes().put("roomId", roomId);
            session.getAttributes().putIfAbsent("nickname", session.getId()); // 기본 닉네임 설정

            mapRooms.computeIfAbsent(roomId, k -> new CopyOnWriteArrayList<>()).add(session);

            // 새로 접속한 유저에게 기존 유저 목록과 좌표 전송
            try {
                sendUserListToNewUser(roomId, session);
            } catch (IOException e) {
                System.err.println("Error sending user list to new user: " + e.getMessage());
            }

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

        // 좌표 정보 초기화
        String userNickname = (String) session.getAttributes().get("nickname");
        userCoordinates.putIfAbsent(userNickname, Map.of("x", 0, "y", 0)); // 기본값 0

        sendNewUserJoinedMessage(roomId, userNickname);
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

        // 좌표 정보 업데이트
        userCoordinates.put(nickname, Map.of("x", x, "y", y));

        // 방 안의 모든 세션에 이동 메시지를 전달
        broadcastMoveUpdate(roomId, nickname, x, y);
        System.out.println(nickname + " moved to x: " + x + ", y: " + y + " in room " + roomId);
    }

    private void broadcastMoveUpdate(String roomId, String nickname, int x, int y) throws IOException {
        Map<String, Object> moveUpdate = Map.of(
                "type", "move",
                "nickname", nickname,
                "x", x,
                "y", y
        );
        broadcastToRoom(roomId, objectMapper.writeValueAsString(moveUpdate));
    }

    private void sendUserListToNewUser(String roomId, WebSocketSession newSession) throws IOException {
        List<WebSocketSession> sessions = mapRooms.get(roomId);
        if (sessions != null && !sessions.isEmpty()) {
            // 기존 사용자들의 닉네임과 좌표 정보를 수집
            List<Map<String, Object>> userList = new CopyOnWriteArrayList<>();
            for (WebSocketSession s : sessions) {
                if (!s.equals(newSession)) {  // 새로 접속한 사용자는 제외
                    String nickname = (String) s.getAttributes().get("nickname");
                    if (nickname == null || nickname.trim().isEmpty()) {
                        nickname = s.getId();  // 기본 닉네임이 세션 ID인 경우
                    }

                    // 유저 좌표 정보
                    Map<String, Integer> coordinates = userCoordinates.getOrDefault(nickname, Map.of("x", 0, "y", 0));

                    Map<String, Object> userInfo = Map.of(
                            "nickname", nickname,
                            "x", coordinates.get("x"),
                            "y", coordinates.get("y")
                    );
                    userList.add(userInfo);
                }
            }

            // 새로 접속한 사용자에게 사용자 목록 전송
            Map<String, Object> userListMessage = Map.of(
                    "type", "user-list",
                    "users", userList
            );

            if (newSession.isOpen()) {
                newSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(userListMessage)));
            }
        }
    }

    private void sendNewUserJoinedMessage(String roomId, String nickname) throws IOException {
        List<WebSocketSession> sessions = mapRooms.get(roomId);
        if (sessions != null) {
            // 새 사용자 접속 알림 메시지
            Map<String, Object> newUserMessage = Map.of(
                    "type", "user-joined",
                    "nickname", nickname
            );

            String message = objectMapper.writeValueAsString(newUserMessage);
            for (WebSocketSession s : sessions) {
                if (s.isOpen() && !s.getAttributes().get("nickname").equals(nickname)) {
                    s.sendMessage(new TextMessage(message));
                }
            }
        }
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
            // 유저 좌표 제거
            String nickname = (String) session.getAttributes().get("nickname");
            if (nickname != null) {
                userCoordinates.remove(nickname);
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

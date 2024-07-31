package com.demo.project.chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatHandler extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.add(session);
        logger.info("New connection established, sessionId: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        synchronized (sessions) {
            for (WebSocketSession webSocketSession : sessions) {
                try {
                    webSocketSession.sendMessage(message);
                } catch (Exception e) {
                    logger.error("Failed to send message to sessionId: {}", webSocketSession.getId(), e);
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        sessions.remove(session);
        logger.info("Connection closed, sessionId: {}, status: {}", session.getId(), status);
    }
}

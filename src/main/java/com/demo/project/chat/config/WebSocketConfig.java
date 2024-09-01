package com.demo.project.chat.config;

import com.demo.project.chat.handler.ChatWebSocketHandler;
import com.demo.project.chat.handler.MapWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 실시간 채팅용 엔드포인트
        registry.addHandler(new ChatWebSocketHandler(), "/ws/chat/{roomId}")
                .setAllowedOrigins("*");


        // 2D 맵용 엔드포인트
        registry.addHandler(new MapWebSocketHandler(), "/ws/map/{roomId}")
                .setAllowedOrigins("*");
    }
}

